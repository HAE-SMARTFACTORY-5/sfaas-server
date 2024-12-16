package com.hae5.sfaas.alerts.dto.response;

import com.hae5.sfaas.alerts.model.AlertAction;
import com.hae5.sfaas.alerts.model.AlertActionDetail;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertActionResponse {
    private String alarmId;
    private String faultDetail;
    private List<ActionDetailInfo> actionDetails;
    private LocalDateTime actionStartTime;
    private LocalDateTime actionCompletionTime;
    private Integer downtime;
    private String completionStatus;

    public static AlertActionResponse from(AlertAction alertAction, List<AlertActionDetail> details) {
        return AlertActionResponse.builder()
                .alarmId(alertAction.getAlarmId())
                .faultDetail(alertAction.getFaultDetail())
                .actionDetails(details.stream()
                        .map(ActionDetailInfo::from)
                        .toList())
                .actionStartTime(alertAction.getActionStartTime())
                .actionCompletionTime(alertAction.getActionCompletionTime())
                .downtime(alertAction.getDowntime())
                .completionStatus(alertAction.getCompletionStatus())
                .build();
    }
}
