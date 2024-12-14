package com.hae5.sfaas.user.dto.response;

import com.hae5.sfaas.user.enums.UserRole;
import lombok.*;

@Getter
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String factoryName;
    private String name;
    private String employeeId;
    private String department;
    private String position;
    private String role;

}
