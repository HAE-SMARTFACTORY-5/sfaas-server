package com.hae5.sfaas.user.model;

import com.hae5.sfaas.auth.dto.request.RegisterRequest;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private Long userId;
    private String employeeId;
    private String password;
    private String role;

    public static User create(String employeeId, String password, String role) {
        return User.builder()
                .employeeId(employeeId)
                .password(password)
                .role(role)
                .build();
    }

    public static User create(RegisterRequest request, String encodedPassword) {
        return User.builder()
                .employeeId(request.employeeId())
                .password(encodedPassword)
                .role(request.userRoleId().toString())
                .build();
    }

}
