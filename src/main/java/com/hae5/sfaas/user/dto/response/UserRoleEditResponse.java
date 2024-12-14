package com.hae5.sfaas.user.dto.response;

import com.hae5.sfaas.user.enums.UserRole;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRoleEditResponse {

    private Long userId;
    private UserRole role;

    public static UserRoleEditResponse of(Long userId, UserRole role) {
        return UserRoleEditResponse.builder()
                .userId(userId)
                .role(role)
                .build();
    }

}
