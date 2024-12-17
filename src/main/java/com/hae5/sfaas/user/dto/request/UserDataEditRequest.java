package com.hae5.sfaas.user.dto.request;

import com.hae5.sfaas.user.enums.UserRole;

public record UserDataEditRequest(Long factoryId, Long departmentId, String position, UserRole role){
}
