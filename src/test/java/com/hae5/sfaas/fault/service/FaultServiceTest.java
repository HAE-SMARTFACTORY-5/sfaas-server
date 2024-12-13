package com.hae5.sfaas.fault.service;

import com.hae5.sfaas.fault.dto.response.FaultResponse;
import com.hae5.sfaas.fault.mapper.FaultMapper;
import com.hae5.sfaas.fault.model.Fault;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FaultServiceTest {

    @InjectMocks
    private FaultService faultService;

    @Mock
    private FaultMapper faultMapper;

    @Test
    @DisplayName("getFault 메소드는 모든 Fault 데이터를 조회하여 FaultResponse로 변환하여 반환한다")
    void getFault_ReturnsAllFaults() {
        // given
        List<Fault> faults = new ArrayList<>();
        Fault f1 = Fault.create("a1", "l1", "p1", "t1", LocalDateTime.now());
        faults.add(f1);
        given(faultMapper.findAll()).willReturn(faults);

        // when
        List<FaultResponse> result = faultService.getFault();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(faults.size());
        verify(faultMapper).findAll();
    }

    @Test
    @DisplayName("getFault 메소드는 데이터가 없을 경우 empty list를 반환한다")
    void getFault_ReturnsEmptyList_WhenNoData() {
        // given
        given(faultMapper.findAll()).willReturn(new ArrayList<>());

        // when
        List<FaultResponse> result = faultService.getFault();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
        verify(faultMapper).findAll();
    }
}