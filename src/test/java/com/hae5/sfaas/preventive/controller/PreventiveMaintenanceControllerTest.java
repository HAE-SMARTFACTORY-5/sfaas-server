package com.hae5.sfaas.preventive.controller;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.preventive.mapper.PreventiveMaintenanceMapper;
import com.hae5.sfaas.preventive.model.PreventiveMaintenance;
import com.hae5.sfaas.user.enums.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class PreventiveMaintenanceControllerTest extends SfaasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PreventiveMaintenanceMapper preventiveMaintenanceMapper;

    @MockitoBean
    private JwtProvider jwtProvider;

    private AccessTokenInfo accessTokenInfo;

    @BeforeEach
    void setUp() {
        accessTokenInfo = AccessTokenInfo.of("1", UserRole.MEMBER.name());
        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
    }

    @AfterEach
    void cleanup() {
        preventiveMaintenanceMapper.deleteAll();
    }

    @Test
    @DisplayName("정상이 아닌 예방점검 목록 조회 테스트")
    void getPreventiveMaintenance() throws Exception {
        // given
        PreventiveMaintenance p1 = PreventiveMaintenance.create(
                1,
                "testequip1",
                LocalDateTime.of(2024, 12, 12, 0, 0),
                LocalDateTime.of(2024, 12, 12, 0, 0),
                "비정상",
                1,
                "진행중",
                LocalDateTime.of(2024, 12, 13, 0, 0));

        PreventiveMaintenance p2 = PreventiveMaintenance.create(
                2,
                "testequip2",
                LocalDateTime.of(2024, 12, 13, 0, 0),
                LocalDateTime.of(2024, 12, 13, 0, 0),
                "정상",
                2,
                "완료",
                LocalDateTime.of(2024, 12, 14, 0, 0));

        preventiveMaintenanceMapper.save(p1);
        preventiveMaintenanceMapper.save(p2);

        // when & then
        mockMvc.perform(get("/api/v1/maintenance/task")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].equipmentId").value("testequip1"))
                .andExpect(jsonPath("$.data[0].inspectResult").value("비정상"))
                .andExpect(jsonPath("$.data[0].status").value("진행중"));
    }

    @Test
    @DisplayName("정상이 아닌 예방점검이 없을 경우 빈 배열을 반환 테스트")
    void getPreventiveMaintenance_ReturnsEmptyArray() throws Exception {
        // given
        PreventiveMaintenance p1 = PreventiveMaintenance.create(
                1,
                "testequip1",
                LocalDateTime.of(2024, 12, 12, 0, 0),
                LocalDateTime.of(2024, 12, 12, 0, 0),
                "정상",
                1,
                "완료",
                LocalDateTime.of(2024, 12, 13, 0, 0));

        preventiveMaintenanceMapper.save(p1);

        // when & then
        mockMvc.perform(get("/api/v1/maintenance/task")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(0)));
    }
}
