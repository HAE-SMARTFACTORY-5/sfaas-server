package com.hae5.sfaas.user.service;

import com.hae5.sfaas.common.exception.ExceptionCode;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.user.dto.request.UserRoleEditRequest;
import com.hae5.sfaas.user.dto.response.UserRoleEditResponse;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hae5.sfaas.common.exception.ExceptionCode.ADMIN_CAN_NOT_DELETE_ERROR;
import static com.hae5.sfaas.common.exception.ExceptionCode.USER_NOT_FOUNT_ERROR;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Transactional
    public void deleteUser(Long userId) {
        User user = userMapper.findById(userId).orElseThrow(() -> SfaasException.create(USER_NOT_FOUNT_ERROR));
        if (user.getRole().equals(UserRole.ADMIN)) {
            throw SfaasException.create(ADMIN_CAN_NOT_DELETE_ERROR);
        }
        userMapper.deleteById(userId);
    }

    @Transactional
    public UserRoleEditResponse editUserRole(Long userId, UserRoleEditRequest request) {
        userMapper.findById(userId).orElseThrow(() -> SfaasException.create(USER_NOT_FOUNT_ERROR));
        userMapper.updateRole(userId, request.role());
        return UserRoleEditResponse.of(userId, request.role());
    }
}
