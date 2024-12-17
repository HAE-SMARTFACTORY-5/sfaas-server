package com.hae5.sfaas.line.service;

import com.hae5.sfaas.common.exception.ExceptionCode;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.common.utils.QuarterUtil;
import com.hae5.sfaas.line.dto.response.*;
import com.hae5.sfaas.line.mapper.LineOperationRateMapper;
import com.hae5.sfaas.line.model.LineOperationRate;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LineOperationRateService {

    private final LineOperationRateMapper lineOperationRateMapper;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public QuarterLineOperationRateResponse getQuarterLineOperationRate(Long userId) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> SfaasException.create(ExceptionCode.USER_NOT_FOUNT_ERROR));
        List<String> nowQuarterMonths = getNowQuarterMonths();

        Map<String, List<LineOperationRate>> collect = getUserFactoryLineOperationRate(user);

        // Month 별 그룹화
        Map<String, List<MonthlyLineOperationRateResponse>> result = new LinkedHashMap<>();
        for (String key : collect.keySet()) {
            List<MonthlyLineOperationRateResponse> monthlyLineOperationRateResponses = transformToMonthly(nowQuarterMonths, collect.get(key));
            result.put(key, monthlyLineOperationRateResponses);
        }

        return QuarterLineOperationRateResponse.of(nowQuarterMonths, result);
    }


    @Transactional(readOnly = true)
    public AllLineOperationRateResponse getAllLineOperationRate(Long userId) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> SfaasException.create(ExceptionCode.USER_NOT_FOUNT_ERROR));
        List<String> nowQuarterMonths = QuarterUtil.getAllMonths();

        Map<String, List<LineOperationRate>> collect = getUserFactoryLineOperationRate(user);

        // Month 별 그룹화
        Map<String, List<MonthlyLineOperationRateResponse>> result = new LinkedHashMap<>();
        for (String key : collect.keySet()) {
            List<MonthlyLineOperationRateResponse> monthlyLineOperationRateResponses = transformToMonthly(nowQuarterMonths, collect.get(key));
            result.put(key, monthlyLineOperationRateResponses);
        }

        return AllLineOperationRateResponse.from(result);
    }

    private Map<String, List<LineOperationRate>> getUserFactoryLineOperationRate(User user) {
        // 공정별 그룹화
        Map<String, List<LineOperationRate>> collect = lineOperationRateMapper.getNowYearLineOperationRate(user.getFactoryId(), LocalDate.now().getYear()).stream()
                .collect(Collectors.groupingBy(
                        LineOperationRate::getProcessId,
                        Collectors.toList()
                ));
        return collect;
    }

    @Transactional(readOnly = true)
    public TotalLineOperationResponse getTotalLineOperation(Long userId) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> SfaasException.create(ExceptionCode.USER_NOT_FOUNT_ERROR));
        Map<String, List<LineOperationRate>> eachLineOperations = lineOperationRateMapper.getNowYearLineOperation(user.getFactoryId(), LocalDate.now().getYear()).stream()
                .collect(Collectors.groupingBy(
                        LineOperationRate::getCategory,
                        Collectors.toList()
                ));
        LineOperationRateResponse plannedLineOperation = getTotalValue(eachLineOperations.get("사업계획"));
        LineOperationRateResponse actualLineOperation = getTotalValue(eachLineOperations.get("종합가동률"));

        return TotalLineOperationResponse.of(plannedLineOperation, actualLineOperation);
    }

    private List<String> getNowQuarterMonths() {
        LocalDate now = LocalDate.now();
        int nowQuarter = QuarterUtil.getNowQuarter(now);
        return QuarterUtil.getQuarterMonths(nowQuarter);
    }

    private List<MonthlyLineOperationRateResponse> transformToMonthly(List<String> targetMonths, List<LineOperationRate> lineOperationRates) {
        // 월별로 데이터 변환
        return targetMonths.stream()
                .map(month -> {
                    // 각 월별로 MonthlyLineOperationRateResponse 생성
                    MonthlyLineOperationRateResponse response = new MonthlyLineOperationRateResponse(month);

                    // lineOperationRate 리스트에서 각 카테고리 값을 설정
                    lineOperationRates.forEach(lineOperationRate -> response.addCategoryValue(lineOperationRate.getCategory(), lineOperationRate.getValueByMonth(month)));

                    return response;
                })
                .collect(Collectors.toList());
    }

    private LineOperationRateResponse getTotalValue(List<LineOperationRate> lineOperationRates) {
        if (lineOperationRates == null || lineOperationRates.size() == 0) {
            return null;
        }
        Long factoryId = lineOperationRates.get(0).getFactoryId();
        Integer year = lineOperationRates.get(0).getYear();;
        Double jan = 0.0;
        Double feb = 0.0;
        Double mar = 0.0;
        Double apr = 0.0;
        Double may = 0.0;
        Double jun = 0.0;
        Double jul = 0.0;
        Double aug = 0.0;
        Double sep = 0.0;
        Double oct = 0.0;
        Double nov = 0.0;
        Double decem = 0.0;
        Long lineId = lineOperationRates.get(0).getLineId();
        String category = lineOperationRates.get(0).getCategory();

        for (LineOperationRate lineOperationRate : lineOperationRates) {
            jan += lineOperationRate.getJan();
            feb += lineOperationRate.getFeb();
            mar += lineOperationRate.getMar();
            apr += lineOperationRate.getApr();
            may += lineOperationRate.getMay();
            jun += lineOperationRate.getJun();
            jul += lineOperationRate.getJul();
            aug += lineOperationRate.getAug();
            sep += lineOperationRate.getSep();
            oct += lineOperationRate.getOct();
            nov += lineOperationRate.getNov();
            decem += lineOperationRate.getDecem();
        }

        return new LineOperationRateResponse(factoryId, year, jan, feb, mar, apr, may, jun, jul,
                aug, sep, oct, nov, decem, lineId, category);
    }
}
