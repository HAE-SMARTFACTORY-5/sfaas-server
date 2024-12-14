package com.hae5.sfaas.maintenance.schedule.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.maintenance.schedule.dto.response.ScheduleResponse;
import com.hae5.sfaas.maintenance.schedule.mapper.ScheduleMapper;
import com.hae5.sfaas.maintenance.schedule.model.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScheduleServiceIntegrationTest extends SfaasApplicationTests {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @AfterEach
    void cleanup() {
        scheduleMapper.deleteAll();
    }

    @Test
    @DisplayName("전체 Schedule 목록을 조회한다")
    void getScheduleTest() {
        // given
        Schedule schedule1 = Schedule.create(1,"line1", "process1", "machine1", "contents1", "remarks1");
        Schedule schedule2 = Schedule.create(2,"line2", "process2", "machine2", "contents2", "remarks2");
        scheduleMapper.save(schedule1);
        scheduleMapper.save(schedule2);

        // when
        List<ScheduleResponse> responses = scheduleService.getSchedule();

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getId()).isEqualTo(schedule1.getId());
        assertThat(responses.get(0).getLine()).isEqualTo("line1");
        assertThat(responses.get(1).getId()).isEqualTo(schedule2.getId());
        assertThat(responses.get(1).getLine()).isEqualTo("line2");
    }

    @Test
    @DisplayName("Schedule ID로 조회 테스트")
    void getScheduleByIdIntegrationTest() {
        // given
        Schedule schedule = Schedule.create(1, "line1", "process1", "machine1", "contents1", "remarks1");
        scheduleMapper.save(schedule);

        // when
        ScheduleResponse response = scheduleService.getScheduleById(schedule.getId());

        // then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(schedule.getId());
        assertThat(response.getLine()).isEqualTo("line1");
    }

    @Test
    @DisplayName("존재하지 않는 Schedule ID 조회시 예외 발생 테스트")
    void getScheduleByIdNotFoundTest() {
        // when & then
        assertThatThrownBy(() -> scheduleService.getScheduleById(999))
                .isInstanceOf(SfaasException.class)
                .hasMessageContaining("존재하지 않는 Maintenance Schedule");
    }
}
