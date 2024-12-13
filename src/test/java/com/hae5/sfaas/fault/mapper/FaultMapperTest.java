package com.hae5.sfaas.fault.mapper;

import com.hae5.sfaas.fault.model.Fault;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FaultMapperTest {

    @Autowired
    private FaultMapper faultMapper;

    @Test
    @DisplayName("DB 연결 테스트")
    void dbConnectionTest() {
        List<Fault> result = faultMapper.findAll();
        assertThat(result).isNotNull();
    }

}