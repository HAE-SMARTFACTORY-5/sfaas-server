package com.hae5.sfaas.process.mapper;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.process.model.Process;
import com.hae5.sfaas.production.dto.response.ProductionPerformanceResponse;
import com.hae5.sfaas.production.model.ProductionPerformance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessMapperTest extends SfaasApplicationTests {

    @Autowired
    private ProcessMapper processMapper;

    @AfterEach
    public void clear() {
        processMapper.deleteAll();
    }

    @DisplayName("Process 저장")
    @Test
    public void saveTest() {
        //given
        Process process = Process.builder()
                .processId(1L)
                .processName("process")
                .description("des")
                .build();

        //when
        processMapper.save(process);

        //then
        assertThat(processMapper.findAll().size()).isEqualTo(1);
    }

    @DisplayName("Process 전체 삭제")
    @Test
    public void deleteAllTest() {
        //given
        Process process = Process.builder()
                .processId(1L)
                .processName("process")
                .description("des")
                .build();
        processMapper.save(process);

        //when
        processMapper.deleteAll();

        //then
        assertThat(processMapper.findAll().size()).isEqualTo(0);
    }

    @DisplayName("Process 전체 조회")
    @Test
    public void findAllTest() {
        //given
        Process process1 = Process.builder()
                .processId(1L)
                .processName("process")
                .description("des")
                .build();
        Process process2 = Process.builder()
                .processId(2L)
                .processName("process")
                .description("des")
                .build();
        processMapper.save(process1);
        processMapper.save(process2);

        //when
        List<Process> processes = processMapper.findAll();

        //then
        assertThat(processes.size()).isEqualTo(2);
    }

}
