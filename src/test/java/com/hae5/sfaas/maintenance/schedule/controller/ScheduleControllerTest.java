package com.hae5.sfaas.maintenance.schedule.controller;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.maintenance.schedule.mapper.ScheduleMapper;
import com.hae5.sfaas.maintenance.schedule.model.Schedule;
import com.hae5.sfaas.user.enums.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ScheduleControllerTest extends SfaasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScheduleMapper scheduleMapper;

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
        scheduleMapper.deleteAll();
    }

    @Test
    @DisplayName("전체 Schedule 목록을 조회한다")
    void getSchedule() throws Exception {
        // given
        Schedule schedule1 = Schedule.create(1,"line1", "process1", "machine1", "contents1", "remarks1");
        Schedule schedule2 = Schedule.create(2,"line2", "process2", "machine2", "contents2", "remarks2");
        scheduleMapper.save(schedule1);
        scheduleMapper.save(schedule2);

        // when & then
        mockMvc.perform(get("/api/v1/maintenance/schedule")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].line").value(schedule1.getLine()))
                .andExpect(jsonPath("$.data[0].process").value(schedule1.getProcess()))
                .andExpect(jsonPath("$.data[0].machine").value(schedule1.getMachine()))
                .andExpect(jsonPath("$.data[1].line").value(schedule2.getLine()))
                .andExpect(jsonPath("$.data[1].process").value(schedule2.getProcess()))
                .andExpect(jsonPath("$.data[1].machine").value(schedule2.getMachine()));
    }

    @Test
    @DisplayName("빈 Schedule 목록을 조회한다")
    void getEmptySchedule() throws Exception {
        mockMvc.perform(get("/api/v1/maintenance/schedule")
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @DisplayName("ID로 특정 Schedule을 조회한다")
    void getScheduleById() throws Exception {
        // given
        Schedule schedule = Schedule.create(1,"line1", "process1", "machine1", "contents1", "remarks1");
        scheduleMapper.save(schedule);

        // when & then
        mockMvc.perform(get("/api/v1/maintenance/schedule/{id}", 1)
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.line").value(schedule.getLine()))
                .andExpect(jsonPath("$.data.process").value(schedule.getProcess()))
                .andExpect(jsonPath("$.data.machine").value(schedule.getMachine()));
    }

    @Test
    @DisplayName("존재하지 않는 Schedule ID 조회시 400 응답을 반환한다")
    void getScheduleById_NotFound() throws Exception {
        mockMvc.perform(get("/api/v1/maintenance/schedule/{id}", 999)
                .header("Authorization", "Bearer test-token"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("-0000"))
                .andExpect(jsonPath("$.message").value("존재하지 않는 Maintenance Schedule"));
    }

    @Test
    @DisplayName("인증되지 않은 요청시 400 에러")
    void getSchedule_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/maintenance/schedule"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("0007"))
                .andExpect(jsonPath("$.message").value("토큰이 없음"));
    }
}
