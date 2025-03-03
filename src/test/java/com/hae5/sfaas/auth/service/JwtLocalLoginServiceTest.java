package com.hae5.sfaas.auth.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.auth.dto.request.LoginRequest;
import com.hae5.sfaas.auth.dto.response.LoginResponse;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class JwtLocalLoginServiceTest extends SfaasApplicationTests {

    @Mock
    private UserMapper userMapper;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private JwtLocalLoginService jwtLocalLoginService;


    @DisplayName("로그인")
    @Test
    void loginTest() {
        // given
        String employeeId = "employeeId";
        String accessToke = "accessToken";
        User newUser = User.builder().userId(1L)
                .employId(employeeId)
                .password("password")
                .role(UserRole.MEMBER)
                .build();
        LoginRequest loginRequest = new LoginRequest(employeeId, "password");

        when(userMapper.findByEmployeeId(eq(employeeId))).thenReturn(Optional.ofNullable(newUser));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);
        when(jwtProvider.createAccessToken(any(Long.class), any(String.class))).thenReturn(accessToke);

        // when
        LoginResponse response = jwtLocalLoginService.login(loginRequest);

        // then
        assertThat(response.getAccessToken()).isEqualTo(accessToke);
        assertThat(response.getUserRole()).isEqualTo(UserRole.MEMBER);
    }

    @DisplayName("존재하지 않는 사원번호일 경우 예외 발생")
    @Test
    void login_not_found_exception_Test() {
        // given
        LoginRequest loginRequest = new LoginRequest("employeeId", "password");

        when(userMapper.findByEmployeeId(any(String.class))).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> jwtLocalLoginService.login(loginRequest))
                .isInstanceOf(SfaasException.class)
                .hasMessageContaining("존재하지 않는 사용자");

    }

    @DisplayName("비밀번호가 일치하지 않을 경우 예외 발생")
    @Test
    void login_password_not_match_exception_Test() {
        // given
        String employeeId = "employeeId";
        User newUser = User.builder().userId(1L)
                .employId(employeeId)
                .password("password")
                .role(UserRole.MEMBER)
                .build();
        LoginRequest loginRequest = new LoginRequest(employeeId, "password");

        when(userMapper.findByEmployeeId(eq(employeeId))).thenReturn(Optional.ofNullable(newUser));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(false);

        // when & then
        assertThatThrownBy(() -> jwtLocalLoginService.login(loginRequest))
                .isInstanceOf(SfaasException.class)
                .hasMessageContaining("비밀번호 불일치");

    }

}
