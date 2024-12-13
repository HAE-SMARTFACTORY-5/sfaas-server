package com.hae5.sfaas.fault.controller;

import com.hae5.sfaas.fault.dto.response.FaultResponse;
import com.hae5.sfaas.fault.model.Fault;
import com.hae5.sfaas.fault.service.FaultService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class FaultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FaultService faultService;

    @Test
    @DisplayName("getFault API 호출 시 200 OK와 데이터 리스트를 반환")
    void getFault_ReturnsList() throws Exception {
        // given
        Fault f1 = Fault.create("a1", "l1", "p1", "t1", LocalDateTime.now());
        Fault f2 = Fault.create("a2", "l2", "p2", "t2", LocalDateTime.now());

        FaultResponse fault1 = FaultResponse.from(f1);
        FaultResponse fault2 = FaultResponse.from(f2);
        List<FaultResponse> faults = Arrays.asList(fault1, fault2);

        when(faultService.getFault()).thenReturn(faults);

        // when & then
        mockMvc.perform(get("/api/v1/fault"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].line").value("l1"))
                .andExpect(jsonPath("$.data[0].process").value("p1"))
                .andExpect(jsonPath("$.data[1].line").value("l2"))
                .andExpect(jsonPath("$.data[1].process").value("p2"));
    }

    @Test
    @DisplayName("getFault API 호출 시 빈 리스트를 반환할 수 있다")
    void getFault_ReturnsEmptyList() throws Exception {
        // given
        given(faultService.getFault()).willReturn(List.of());

        // when & then
        mockMvc.perform(get("/api/v1/fault"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}