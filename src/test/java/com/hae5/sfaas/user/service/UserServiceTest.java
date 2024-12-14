package com.hae5.sfaas.user.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.user.dto.request.UserRoleEditRequest;
import com.hae5.sfaas.user.dto.response.UserRoleEditResponse;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UserServiceTest extends SfaasApplicationTests {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @DisplayName("사용자 삭제")
    @Test
    public void deleteUserTest () {
        //given
        User user = User.builder()
                .userId(1L)
                .employeeId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        when(userMapper.findById(eq(user.getUserId()))).thenReturn(Optional.ofNullable(user));

        //when
        userService.deleteUser(user.getUserId());

        //then
        verify(userMapper).deleteById(user.getUserId());
    }

    @DisplayName("사용자 삭제 시, 존재하지 않은 경우")
    @Test
    public void deleteUser_User_Not_Found_Error_Test () {
        //given
        User user = User.builder()
                .userId(1L)
                .employeeId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        when(userMapper.findById(eq(user.getUserId()))).thenReturn(Optional.empty());

        //when & then
        assertThatThrownBy(() -> userService.deleteUser(user.getUserId()))
                .isInstanceOf(SfaasException.class)
                .hasMessageContaining("존재하지 않는 사용자");
    }

    @DisplayName("사용자 삭제 시, 관리자를 삭제하려고 했을 경우")
    @Test
    public void deleteUser_Admin_Can_Not_Delete_Error_Test () {
        //given
        User user = User.builder()
                .userId(1L)
                .employeeId("test")
                .password("pwd")
                .role(UserRole.ADMIN)
                .build();

        when(userMapper.findById(eq(user.getUserId()))).thenReturn(Optional.ofNullable(user));

        //when & then
        assertThatThrownBy(() -> userService.deleteUser(user.getUserId()))
                .isInstanceOf(SfaasException.class)
                .hasMessageContaining("관리자 삭제 불가");
    }

    @DisplayName("사용자 권한 업데이트")
    @Test
    public void updateUserRoleTest () {
        //given
        UserRoleEditRequest request = new UserRoleEditRequest(UserRole.ADMIN);
        User user = User.builder()
                .userId(1L)
                .employeeId("test")
                .password("pwd")
                .role(UserRole.ADMIN)
                .build();

        when(userMapper.findById(eq(user.getUserId()))).thenReturn(Optional.ofNullable(user));

        //when
        UserRoleEditResponse response = userService.updateUserRole(user.getUserId(), request);

        //then
        verify(userMapper).updateRole(user.getUserId(), request.role());
        assertThat(response.getUserId()).isEqualTo(user.getUserId());
        assertThat(response.getRole()).isEqualTo(request.role());

    }

    @DisplayName("사용자 권한 변경 시, 존재하지 않은 경우")
    @Test
    public void updateUserRole_User_Not_Found_Error_Test () {
        //given
        UserRoleEditRequest request = new UserRoleEditRequest(UserRole.ADMIN);
        User user = User.builder()
                .userId(1L)
                .employeeId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        when(userMapper.findById(eq(user.getUserId()))).thenReturn(Optional.empty());

        //when & then
        assertThatThrownBy(() -> userService.updateUserRole(user.getUserId(), request))
                .isInstanceOf(SfaasException.class)
                .hasMessageContaining("존재하지 않는 사용자");
    }

}
