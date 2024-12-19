package com.hae5.sfaas.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.common.response.PaginationResponse;
import com.hae5.sfaas.user.dto.request.UserDataEditRequest;
import com.hae5.sfaas.user.dto.response.UserResponse;
import com.hae5.sfaas.user.dto.response.UserDataEditResponse;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import com.hae5.sfaas.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .employId("test")
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
                .employId("test")
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
                .andExpect(jsonPath("$.errorCode").value("0009"))
                .andExpect(jsonPath("$.message").value("접근 권한 없음"));
    }

    @DisplayName("사용자 정보 변경")
    @Test
    public void updateUserTest() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.ADMIN)
                .build();

        UserRole expectRole = UserRole.ADMIN;
        Long expectFactoryId = 1L;
        Long expectDepartmentId = 1L;
        String expectPosition = "position";

        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());
        UserDataEditRequest request = new UserDataEditRequest(expectFactoryId, expectDepartmentId, expectPosition    , expectRole);
        UserDataEditResponse response = UserDataEditResponse.of(user.getUserId(), expectFactoryId, expectDepartmentId, expectPosition    , expectRole);

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(userService.updateUserData(any(Long.class), any(UserDataEditRequest.class))).thenReturn(response);

        //when & then
        mockMvc.perform(patch("/api/v1/user/data/{userId}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId").value(user.getUserId()))
                .andExpect(jsonPath("$.data.factoryId").value(expectFactoryId))
                .andExpect(jsonPath("$.data.departmentId").value(expectDepartmentId))
                .andExpect(jsonPath("$.data.position").value(expectPosition))
                .andExpect(jsonPath("$.data.role").value(expectRole.name()));
    }

    @DisplayName("사용자 정보 변경 시, 관리자가 아닐 경우")
    @Test
    public void updateUserTest_Not_Admin_Test() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        UserRole expectRole = UserRole.ADMIN;

        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());
        UserDataEditRequest request = new UserDataEditRequest(1L, 1L, "position"    , expectRole);
        UserDataEditResponse response = UserDataEditResponse.of(user.getUserId(), 1L, 1L, "position"    , expectRole);

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(userService.updateUserData(any(Long.class), any(UserDataEditRequest.class))).thenReturn(response);

        //when & then
        mockMvc.perform(patch("/api/v1/user/role/{userId}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("0009"))
                .andExpect(jsonPath("$.message").value("접근 권한 없음"));
    }

    @DisplayName("사용자 검색")
    @Test
    public void searchUser_Test() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.ADMIN)
                .build();

        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());
        List<UserResponse> result = new ArrayList<>();
        result.add(new UserResponse(1L, "factory", "name", "eployee", "department", "position", "ADMIN"));
        PageRequest pageable = PageRequest.of(0, 1);
        Page<UserResponse> page = new PageImpl<>(result, pageable, 1);
        PaginationResponse<UserResponse> response = PaginationResponse.create(page, result);

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(userService.searchUser(any(String.class), any(String.class), any(Pageable.class))).thenReturn(response);

        //when & then
        mockMvc.perform(get("/api/v1/user/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken")
                        .param("keyword", "keyword")
                        .param("type", "type")
                        .param("size", "1")
                        .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.hasPrevious").value(false))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andExpect(jsonPath("$.data.totalRecordCount").value(1))
                .andExpect(jsonPath("$.data.totalPageCount").value(1))
                .andExpect(jsonPath("$.data.nowPage").value(0));
    }

    @DisplayName("사용자 검색 시 권한 확인")
    @Test
    public void searchUser_Role_Error_Test() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());
        List<UserResponse> result = new ArrayList<>();
        result.add(new UserResponse(1L, "factory", "name", "eployee", "department", "position", "ADMIN"));
        PageRequest pageable = PageRequest.of(0, 1);
        Page<UserResponse> page = new PageImpl<>(result, pageable, 1);
        PaginationResponse<UserResponse> response = PaginationResponse.create(page, result);

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(userService.searchUser(any(String.class), any(String.class), any(Pageable.class))).thenReturn(response);

        //when & then
        mockMvc.perform(get("/api/v1/user/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken")
                        .param("keyword", "keyword")
                        .param("type", "type")
                        .param("size", "1")
                        .param("page", "0"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("0009"))
                .andExpect(jsonPath("$.message").value("접근 권한 없음"));
    }

}
