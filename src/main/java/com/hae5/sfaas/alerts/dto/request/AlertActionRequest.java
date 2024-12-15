package com.hae5.sfaas.alerts.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlertActionRequest {
    private String maintenanceStaff;
    private LocalDateTime actionStartTime;
    private LocalDateTime actionCompletionTime;
    private String completionStatus;
    private String actionDetails;
    private String faultDetail;
}
