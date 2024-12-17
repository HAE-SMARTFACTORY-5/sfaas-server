package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.alerts.dto.response.TotalAlarmHistoryResponse;
import com.hae5.sfaas.alerts.mapper.AlarmHistoryMapper;
import com.hae5.sfaas.alerts.model.AlarmHistory;
import com.hae5.sfaas.common.exception.ExceptionCode;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmHistoryService {

    private final AlarmHistoryMapper alarmHistoryMapper;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public TotalAlarmHistoryResponse getNowYearTotalAlarmHistory(Long userId) {
        User user = userMapper.findById(userId)
                    .orElseThrow(() -> SfaasException.create(ExceptionCode.USER_NOT_FOUNT_ERROR));
        Map<Integer, List<AlarmHistory>> alarms = alarmHistoryMapper.getNowYearTotalAlarmHistory(LocalDate.now().getYear()).stream()
                .collect(Collectors.groupingBy(
                        alarmHistory -> alarmHistory.getOccurrenceTime().getMonth().getValue(),
                        Collectors.toList()
                ));


        return convertToTotalAlarmHistoryResponse(alarms);
    }

    private TotalAlarmHistoryResponse convertToTotalAlarmHistoryResponse(Map<Integer, List<AlarmHistory>> alarms) {
        if (alarms.size() == 0) {
            return null;
        }
        TotalAlarmHistoryResponse response = new TotalAlarmHistoryResponse();
        for (Integer monthValue : alarms.keySet()) {
            response.addMonthValue(monthValue, alarms.get(monthValue).size());
        }
        return response;
    }
}
