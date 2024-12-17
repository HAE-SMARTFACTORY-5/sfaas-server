package com.hae5.sfaas.user.service;

import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.common.response.PaginationResponse;
import com.hae5.sfaas.user.dto.request.UserDataEditRequest;
import com.hae5.sfaas.user.dto.response.UserResponse;
import com.hae5.sfaas.user.dto.response.UserDataEditResponse;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public UserDataEditResponse updateUserData(Long userId, UserDataEditRequest request) {
        userMapper.findById(userId).orElseThrow(() -> SfaasException.create(USER_NOT_FOUNT_ERROR));
        userMapper.updateRole(userId, request);
        return UserDataEditResponse.of(userId, request.factoryId(),
                                            request.departmentId(), request.position(), request.role());
    }

    @Transactional(readOnly = true)
    public PaginationResponse<UserResponse> searchUser(String keyword, String type, Pageable pageable) {
        List<UserResponse> content = userMapper.findByKeyword(keyword, type, pageable.getPageSize(), pageable.getOffset());
        Page<UserResponse> page = PageableExecutionUtils.getPage(content, pageable, () -> userMapper.countByKeyword(keyword, type));
        return PaginationResponse.create(page, content);
    }
}
