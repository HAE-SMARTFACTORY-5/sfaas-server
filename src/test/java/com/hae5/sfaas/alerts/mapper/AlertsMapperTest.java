package com.hae5.sfaas.alerts.mapper;

import com.hae5.sfaas.alerts.model.Alerts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AlertsMapperTest {

    @Autowired
    private AlertsMapper alertsMapper;

    @Test
    @DisplayName("DB 연결 테스트")
    void dbConnectionTest() {
        List<Alerts> result = alertsMapper.findAll();
        assertThat(result).isNotNull();
    }

}