package com.hae5.sfaas.failure.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.monthly.failure.dto.response.FailureRateResponse;
import com.hae5.sfaas.monthly.failure.mapper.MonthlyFailureMapper;
import com.hae5.sfaas.monthly.failure.model.MonthlyFailure;
import com.hae5.sfaas.monthly.failure.service.MonthlyFailureService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MonthlyFailureServiceTest extends SfaasApplicationTests {

    @Autowired
    protected MonthlyFailureMapper monthlyFailureMapper;

    @Autowired
    protected MonthlyFailureService monthlyFailureService;

    @AfterEach
    public void clear() {
        monthlyFailureMapper.deleteAll();
    }

    @Test
    @DisplayName("Failure 저장 확인")
    public void saveTest() {
        // given
        MonthlyFailure monthlyFailure = MonthlyFailure.create("PR-1", "PR", 1, 0.1, 0.1,
                0.1, 0.1, 20D, 2, 10D);

        // when
        monthlyFailureMapper.save(monthlyFailure);

        // then
        MonthlyFailure savedMonthlyFailure = monthlyFailureMapper.findAll().get(0);
        assertThat(savedMonthlyFailure).isNotNull();
        assertThat(savedMonthlyFailure.getProcess()).isEqualTo("PR");
        assertThat(savedMonthlyFailure.getFailureRatePlan()).isEqualTo(0.1);
    }

    @Test
    @DisplayName("모든 Failure 데이터를 조회할 수 있다")
    void getFailure_ReturnsAllFailure() {
        // given
        MonthlyFailure f1 = MonthlyFailure.create("PR-1", "PR", 1, 0.1, 0.1,
                0.1, 0.1, 20D, 2, 10D);
        MonthlyFailure f2 = MonthlyFailure.create("CB-1", "CB", 1, 0.1, 0.1,
                0.1, 0.1, 20D, 2, 10D);
        monthlyFailureMapper.save(f1);
        monthlyFailureMapper.save(f2);

        // when
        Map<String, List<FailureRateResponse>> result = monthlyFailureService.getAllFailureRates();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get("PR").get(0).getProcess()).isEqualTo("PR");
        assertThat(result.get("CB").get(0).getProcess()).isEqualTo("CB");
    }

    @Test
    @DisplayName("특정 공정의 불량률 데이터를 조회할 수 있다")
    void getFailureRatesByProcess_ReturnsList() {
        // given
        MonthlyFailure f1 = MonthlyFailure.create("PR-1", "PR", 1, 0.1, 0.1,
                0.1, 0.1, 20D, 2, 10D);
        MonthlyFailure f2 = MonthlyFailure.create("PR-2", "PR", 2, 0.2, 0.2,
                0.2, 0.2, 30D, 3, 15D);
        monthlyFailureMapper.save(f1);
        monthlyFailureMapper.save(f2);

        // when
        List<FailureRateResponse> result = monthlyFailureService.getFailureRatesByProcess("PR");

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getProcess()).isEqualTo("PR");
        assertThat(result.get(0).getFailureMonth()).isEqualTo(1);
        assertThat(result.get(0).getFailureRatePlan()).isEqualTo(0.1);
        assertThat(result.get(1).getProcess()).isEqualTo("PR");
        assertThat(result.get(1).getFailureMonth()).isEqualTo(2);
        assertThat(result.get(1).getFailureRatePlan()).isEqualTo(0.2);
    }

    @Test
    @DisplayName("존재하지 않는 공정 조회 시 빈 리스트를 반환한다")
    void getFailureRatesByProcess_ReturnsEmptyList() {
        // given
        String nonExistentProcess = "NONEXISTENT";

        // when
        List<FailureRateResponse> result = monthlyFailureService.getFailureRatesByProcess(nonExistentProcess);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}