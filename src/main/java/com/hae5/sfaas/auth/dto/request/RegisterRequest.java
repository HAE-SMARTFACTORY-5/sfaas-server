package com.hae5.sfaas.auth.dto.request;

public record RegisterRequest(Long factoryId, String name, String employeeId, String password,
                              Long departmentId, Long positionId, Long userRoleId) {
}
