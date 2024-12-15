package com.hae5.sfaas.user.model;

import com.hae5.sfaas.auth.dto.request.RegisterRequest;
import com.hae5.sfaas.user.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private Long userId;
    private Long factoryId;
    private String name;
    private String employId;
    private String password;
    private Long departmentId;
    private String position;
    private UserRole role;
    private LocalDateTime createdAt;

    public static User create(String employeeId, String password, UserRole role) {
        return User.builder()
                .employId(employeeId)
                .password(password)
                .role(role)
                .build();
    }

    public static User create(RegisterRequest request, String encodedPassword) {
        return User.builder()
                .factoryId(request.factoryId())
                .name(request.name())
                .employId(request.employeeId())
                .password(encodedPassword)
                .departmentId(request.departmentId())
                .position(request.position())
                .role(request.userRole())
                .build();
    }

}
