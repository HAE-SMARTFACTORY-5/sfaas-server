package com.hae5.sfaas.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import com.hae5.sfaas.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class UserControllerTest extends SfaasApplicationTests {

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtProvider jwtProvider;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("사용자 삭제")
    @Test
    public void deleteUserTest() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employeeId("test")
                .password("pwd")
                .role(UserRole.ADMIN)
                .build();
        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        doNothing().when(userService).deleteUser(any(Long.class));

        //when & then
        mockMvc.perform(delete("/api/v1/user/{userId}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken"))
                .andExpect(status().isOk());
    }

    @DisplayName("사용자 삭제 시, 관리자가 아닐 경우")
    @Test
    public void deleteUser_Not_Admin_Test() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employeeId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();
        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        doNothing().when(userService).deleteUser(any(Long.class));

        //when & then
        mockMvc.perform(delete("/api/v1/user/{userId}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("0008"))
                .andExpect(jsonPath("$.message").value("접근 권한 없음"));
    }

}
