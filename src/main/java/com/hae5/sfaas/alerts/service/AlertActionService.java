package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.alerts.dto.request.AlertActionRequest;
import com.hae5.sfaas.alerts.dto.response.AlertActionResponse;
import com.hae5.sfaas.alerts.mapper.AlertActionMapper;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.AlertAction;
import com.hae5.sfaas.alerts.model.AlertActionDetail;
import com.hae5.sfaas.common.exception.SfaasException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.hae5.sfaas.common.exception.ExceptionCode.ALARM_NOT_FOUND_ERROR;

@Service
@RequiredArgsConstructor
public class AlertActionService {
    private final AlertActionMapper alertActionMapper;
    private final AlertsMapper alertsMapper;

    // 일치하는 alarmId 값을 가진 AlarmAction 데이터를 가져오기
    @Transactional(readOnly = true)
    public AlertActionResponse getAlertByAlarmId(String alarmId) {
        AlertAction alertAction = alertActionMapper.findByAlarmId(alarmId).orElseThrow(
                () -> SfaasException.create(ALARM_NOT_FOUND_ERROR));
        List<AlertActionDetail> details = alertActionMapper.findDetailsByAlarmId(alarmId);
        return AlertActionResponse.from(alertAction, details);
    }

    //
    @Transactional
    public AlertActionResponse createAlertAction(String alarmId, AlertActionRequest request) {
        // 알람 ID 존재 여부 확인
        alertsMapper.findById(alarmId)
                .orElseThrow(() -> SfaasException.create(ALARM_NOT_FOUND_ERROR));

        // 기존 조치 내역 삭제
        // 원래는 기존 내역 확인하고 달라진 내역 확인하여 update 해야하지만
        // 우선 수정과 추가 과정을 간략하기 진행하기 위해 임시적인 조치
        // 추후 수정 예정
        alertActionMapper.deleteAllDetailsByAlarmId(alarmId);

        // 새로운 조치 내역 정보 저장
        AlertAction alertAction = AlertAction.builder()
                .alarmId(alarmId)
                .faultDetail(request.getFaultDetail())
                .actionStartTime(request.getActionStartTime())
                .actionCompletionTime(request.getActionCompletionTime())
                .completionStatus(request.getCompletionStatus())
                .downtime(calculateDowntime(request.getActionStartTime(), request.getActionCompletionTime()))
                .build();

        alertActionMapper.save(alertAction);

        // 상세 조치 내역 저장
        if (request.getActionDetails() != null) {
            for (int i = 0; i < request.getActionDetails().size(); i++) {
                var detail = request.getActionDetails().get(i);
                AlertActionDetail actionDetail = AlertActionDetail.create(
                        alarmId,
                        detail.getFaultType(),
                        detail.getActionDetail(),
                        detail.getMaintenanceStaff(),
                        detail.getActionTime(),
                        i + 1);
                alertActionMapper.saveActionDetail(actionDetail);
            }
        }

        // 전체 데이터 조회 및 응답
        List<AlertActionDetail> details = alertActionMapper.findDetailsByAlarmId(alarmId);
        return AlertActionResponse.from(alertAction, details);
    }

    // 조치 완료 시간 - 알람 발생 시간
    private Integer calculateDowntime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return null;
        }
        return (int) Duration.between(startTime, endTime).toMinutes();
    }
}
