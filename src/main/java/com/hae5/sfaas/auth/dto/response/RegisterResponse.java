package com.hae5.sfaas.auth.dto.response;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterResponse {

    private Long userId;

    public static RegisterResponse from(Long userId) {
        return RegisterResponse.builder()
                .userId(userId)
                .build();
    }
    
}
