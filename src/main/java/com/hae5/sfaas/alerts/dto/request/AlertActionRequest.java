package com.hae5.sfaas.alerts.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertActionRequest {
    private LocalDateTime actionStartTime;
    private LocalDateTime actionCompletionTime;
    private String completionStatus;
    private String faultDetail;
    private List<AlertActionDetailRequest> actionDetails;

    public static AlertActionRequest from(
            LocalDateTime actionStartTime,
            LocalDateTime actionCompletionTime,
            String completionStatus,
            String faultDetail,
            List<AlertActionDetailRequest> actionDetails) {
        return AlertActionRequest.builder()
                .actionStartTime(actionStartTime)
                .actionCompletionTime(actionCompletionTime)
                .completionStatus(completionStatus)
                .faultDetail(faultDetail)
                .actionDetails(actionDetails)
                .build();
    }
}
