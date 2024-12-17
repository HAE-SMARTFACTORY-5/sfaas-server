package com.hae5.sfaas.alerts.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.alerts.dto.response.TotalAlarmHistoryResponse;
import com.hae5.sfaas.alerts.mapper.AlarmHistoryMapper;
import com.hae5.sfaas.alerts.model.AlarmHistory;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AlarmHistoryServiceTest extends SfaasApplicationTests {

    @Mock
    private AlarmHistoryMapper alarmHistoryMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AlarmHistoryService alarmHistoryService;

    @DisplayName("올해 알람 히스토리 조회")
    @Test
    public void getNowYearTotalAlarmHistoryTest () {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();
        AlarmHistory alarmHistory1 = AlarmHistory.builder()
                .alarmId(1L)
                .lineId("gran")
                .processId("CT")
                .categoryL3Id(1L)
                .occurrenceTime(LocalDateTime.of(2024, 1, 1, 1, 1))
                .status("ACTIVE")
                .build();
        AlarmHistory alarmHistory2 = AlarmHistory.builder()
                .alarmId(2L)
                .lineId("gran")
                .processId("CT")
                .categoryL3Id(1L)
                .occurrenceTime(LocalDateTime.of(2024, 1, 1, 1, 1))
                .status("ACTIVE")
                .build();

        List<AlarmHistory> alarmHistories = new ArrayList<>();
        alarmHistories.add(alarmHistory1);
        alarmHistories.add(alarmHistory2);

        when(alarmHistoryMapper.getNowYearTotalAlarmHistory(any(Integer.class))).thenReturn(alarmHistories);
        when(userMapper.findById(any(Long.class))).thenReturn(Optional.ofNullable(user));

        //when
        TotalAlarmHistoryResponse nowYearTotalAlarmHistory = alarmHistoryService.getNowYearTotalAlarmHistory(user.getUserId());

        //then
        Assertions.assertThat(nowYearTotalAlarmHistory.getJan()).isEqualTo(2);
    }

}
