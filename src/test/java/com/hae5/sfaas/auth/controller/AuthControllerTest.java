package com.hae5.sfaas.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.auth.dto.request.LoginRequest;
import com.hae5.sfaas.auth.dto.request.RegisterRequest;
import com.hae5.sfaas.auth.dto.response.LoginResponse;
import com.hae5.sfaas.auth.dto.response.RegisterResponse;
import com.hae5.sfaas.auth.service.AuthService;
import com.hae5.sfaas.user.enums.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class AuthControllerTest extends SfaasApplicationTests {

    @MockitoBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @DisplayName("로컬 로그인 요청")
    @Test
    void login_test() throws Exception {
        // given
        LoginRequest request = new LoginRequest("employeeId", "password");
        LoginResponse response = LoginResponse.from("accessToken", UserRole.MEMBER);

        when(authService.login(eq(request))).thenReturn(response);

        // when & then
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.data.userRole").value(UserRole.MEMBER.toString()));
    }

    @DisplayName("사용자 등록 요청")
    @Test
    void register_test() throws Exception {
        // given
        RegisterRequest request = new RegisterRequest(1L, "name", "employeeId",
                "password", 1L, "position", UserRole.MEMBER);
        RegisterResponse response = RegisterResponse.from(1L);

        when(authService.register(eq(request))).thenReturn(response);

        // when & then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId").value(1L));
    }

}
