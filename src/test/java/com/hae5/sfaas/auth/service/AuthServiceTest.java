package com.hae5.sfaas.auth.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.auth.dto.request.LoginRequest;
import com.hae5.sfaas.auth.dto.request.RegisterRequest;
import com.hae5.sfaas.auth.dto.response.LoginResponse;
import com.hae5.sfaas.auth.dto.response.RegisterResponse;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AuthServiceTest extends SfaasApplicationTests {

    @Mock
    private UserMapper userMapper;

    @Mock
    private LocalLoginService localLoginService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;


    @DisplayName("로그인")
    @Test
    void loginTest() {
        // given
        LoginRequest loginRequest = new LoginRequest("employeeId", "password");
        LoginResponse expectedResponse = LoginResponse.from("accessToken", UserRole.MEMBER);

        when(localLoginService.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        // when
        LoginResponse response = authService.login(loginRequest);


        // then
        assertThat(response.getAccessToken()).isEqualTo(expectedResponse.getAccessToken());
        assertThat(response.getUserRole()).isEqualTo(expectedResponse.getUserRole());
    }

    @DisplayName("회원가입")
    @Test
    void registerTest() {
        // given
        RegisterRequest registerRequest = new RegisterRequest(1L, "name", "employeeId",
                "password", 1L, "position", UserRole.MEMBER);
        User mockUser = User.create(registerRequest, "encodedPassword");

        when(passwordEncoder.encode(registerRequest.password())).thenReturn("encodedPassword");
        doNothing().when(userMapper).save(any(User.class));

        // when
        RegisterResponse actualResponse = authService.register(registerRequest);

        // then
        assertThat(actualResponse.getUserId()).isEqualTo(mockUser.getUserId());
    }
}

