package com.hae5.sfaas.auth.dto.request;

import com.hae5.sfaas.user.enums.UserRole;

public record RegisterRequest(Long factoryId, String name, String employeeId, String password,
                              Long departmentId, String position, UserRole userRole) {
}
