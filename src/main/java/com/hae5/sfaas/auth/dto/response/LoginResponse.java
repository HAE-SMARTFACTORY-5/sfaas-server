package com.hae5.sfaas.auth.dto.response;

import com.hae5.sfaas.user.enums.UserRole;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private String accessToken;
    private UserRole userRole;

    public static LoginResponse from(String accessToken, UserRole userRole) {
        return LoginResponse.builder()
                .accessToken(accessToken)
                .userRole(userRole)
                .build();
    }

}
