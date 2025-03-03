package com.hae5.sfaas.common.jwt;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenInfo {
    private final String userId;
    private final String role;

    public static AccessTokenInfo of(String userId, String role) {
        return new AccessTokenInfo(userId, role);
    }
}
