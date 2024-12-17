package com.hae5.sfaas.alerts.mapper;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.alerts.model.AlarmHistory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AlarmHistoryMapperTest extends SfaasApplicationTests {
    
    @Autowired
    private AlarmHistoryMapper alarmHistoryMapper;

    @AfterEach
    public void clear() {
        alarmHistoryMapper.deleteAll();
    }

    @DisplayName("AlarmHistory 저장")
    @Test
    public void saveTest() {
        //given
            AlarmHistory alarmHistory = AlarmHistory.builder()
                    .alarmId(1L)
                    .lineId("gran")
                    .processId("CT")
                    .categoryL3Id(1L)
                    .occurrenceTime(LocalDateTime.now())
                    .resolutionTime(LocalDateTime.now())
                    .status("ACTIVE")
                    .build();

        //when
        alarmHistoryMapper.save(alarmHistory);

        //then
        assertThat(alarmHistoryMapper.findAll().size()).isEqualTo(1);
    }

    @DisplayName("AlarmHistory 전체 삭제")
    @Test
    public void deleteAllTest() {
        //given
        AlarmHistory alarmHistory = AlarmHistory.builder()
                .alarmId(1L)
                .lineId("gran")
                .processId("CT")
                .categoryL3Id(1L)
                .occurrenceTime(LocalDateTime.now())
                .resolutionTime(LocalDateTime.now())
                .status("ACTIVE")
                .build();
        alarmHistoryMapper.save(alarmHistory);

        //when
        alarmHistoryMapper.deleteAll();

        //then
        assertThat(alarmHistoryMapper.findAll().size()).isEqualTo(0);
    }

    @DisplayName("AlarmHistory 전체 조회")
    @Test
    public void findAllTest() {
        //given
        AlarmHistory alarmHistory1 = AlarmHistory.builder()
                .alarmId(1L)
                .lineId("gran")
                .processId("CT")
                .categoryL3Id(1L)
                .occurrenceTime(LocalDateTime.now())
                .resolutionTime(LocalDateTime.now())
                .status("ACTIVE")
                .build();
        AlarmHistory alarmHistory2 = AlarmHistory.builder()
                .alarmId(2L)
                .lineId("gran")
                .processId("CT")
                .categoryL3Id(1L)
                .occurrenceTime(LocalDateTime.now())
                .resolutionTime(LocalDateTime.now())
                .status("ACTIVE")
                .build();

        alarmHistoryMapper.save(alarmHistory1);
        alarmHistoryMapper.save(alarmHistory2);

        //when
        List<AlarmHistory> alarmHistories = alarmHistoryMapper.findAll();

        //then
        assertThat(alarmHistories.size()).isEqualTo(2);
    }

    @DisplayName("올해 Alarm History 내역 조회")
    @Test
    public void getNowYearTotalAlarmHistoryTest() {
        //given
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
                .occurrenceTime(LocalDateTime.of(2023, 1, 1, 1, 1))
                .status("ACTIVE")
                .build();

        alarmHistoryMapper.save(alarmHistory1);
        alarmHistoryMapper.save(alarmHistory2);

        //when
        List<AlarmHistory> alarmHistories = alarmHistoryMapper.getNowYearTotalAlarmHistory(2024);

        //then
        assertThat(alarmHistories.size()).isEqualTo(1);
    }
}
