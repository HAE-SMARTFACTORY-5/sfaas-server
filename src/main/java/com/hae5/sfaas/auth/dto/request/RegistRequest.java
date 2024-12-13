package com.hae5.sfaas.auth.dto.request;

public record RegistRequest(Long factoryId, String name, String employeeId,
                            Long departmentId, Long positionId, Long userRoleId) {
}
