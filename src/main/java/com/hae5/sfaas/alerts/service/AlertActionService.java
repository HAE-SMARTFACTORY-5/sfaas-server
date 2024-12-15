package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.alerts.dto.request.AlertActionRequest;
import com.hae5.sfaas.alerts.dto.response.AlertActionResponse;
import com.hae5.sfaas.alerts.mapper.AlertActionMapper;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.AlertAction;
import com.hae5.sfaas.common.exception.SfaasException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;

import static com.hae5.sfaas.common.exception.ExceptionCode.ALARM_NOT_FOUND_ERROR;

@Service
@RequiredArgsConstructor
public class AlertActionService {
    private final AlertActionMapper alertActionMapper;
    private final AlertsMapper alertsMapper;

    @Transactional
    public AlertActionResponse createAlertAction(String alarmId, AlertActionRequest request) {
        // 알람 ID 존재 여부 확인
        alertsMapper.findById(alarmId)
                .orElseThrow(() -> SfaasException.create(ALARM_NOT_FOUND_ERROR));

        // downtime 계산
        Integer downtime = calculateDowntime(request.getActionStartTime(),
                request.getActionCompletionTime());

        AlertAction alertAction = AlertAction.create(
                alarmId,
                request.getFaultDetail(),
                request.getMaintenanceStaff(),
                request.getActionDetails(),
                request.getActionStartTime(),
                request.getActionCompletionTime(),
                downtime,
                request.getCompletionStatus());

        alertActionMapper.save(alertAction);
        return AlertActionResponse.from(alertAction);
    }

    private Integer calculateDowntime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return null;
        }
        return (int) Duration.between(startTime, endTime).toMinutes();
    }
}
