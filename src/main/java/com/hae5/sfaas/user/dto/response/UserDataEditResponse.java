package com.hae5.sfaas.user.dto.response;

import com.hae5.sfaas.user.enums.UserRole;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDataEditResponse {

    private Long userId;
    Long factoryId;
    Long departmentId;
    String position;
    UserRole role;

    public static UserDataEditResponse of(Long userId, Long factoryId, Long departmentId, String position, UserRole role) {
        return UserDataEditResponse.builder()
                .userId(userId)
                .factoryId(factoryId)
                .departmentId(departmentId)
                .position(position)
                .role(role)
                .build();
    }

}
