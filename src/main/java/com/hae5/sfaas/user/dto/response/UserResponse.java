package com.hae5.sfaas.user.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long userId;
    private String factoryName;
    private String name;
    private String employeeId;
    private String department;
    private String position;
    private String role;

}
