package com.hae5.sfaas.line.service;

import com.hae5.sfaas.common.exception.ExceptionCode;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.common.utils.QuarterUtil;
import com.hae5.sfaas.line.dto.response.MonthlyLineOperationRateResponse;
import com.hae5.sfaas.line.dto.response.QuarterLineOperationRateResponse;
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

        Map<String, List<LineOperationRate>> collect = getAllLineOperationRate(user);

        // Month 별 그룹화
        Map<String, List<MonthlyLineOperationRateResponse>> result = new LinkedHashMap<>();
        for (String key : collect.keySet()) {
            List<MonthlyLineOperationRateResponse> monthlyLineOperationRateResponses = transformToMonthly(nowQuarterMonths, collect.get(key));
            result.put(key, monthlyLineOperationRateResponses);
        }

        return QuarterLineOperationRateResponse.of(nowQuarterMonths, result);
    }

    private Map<String, List<LineOperationRate>> getAllLineOperationRate(User user) {
        // 공정별 그룹화
        Map<String, List<LineOperationRate>> collect = lineOperationRateMapper.getQuarterLineOperationRate(user.getFactoryId(), LocalDate.now().getYear()).stream()
                .collect(Collectors.groupingBy(
                        LineOperationRate::getProcessId,
                        Collectors.toList()
                ));
        return collect;
    }

    private List<String> getNowQuarterMonths() {
        int nowQuarter = QuarterUtil.getNowQuarter();
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
}
