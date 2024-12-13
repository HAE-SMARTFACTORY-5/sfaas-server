package com.hae5.sfaas.user.model;

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

}
