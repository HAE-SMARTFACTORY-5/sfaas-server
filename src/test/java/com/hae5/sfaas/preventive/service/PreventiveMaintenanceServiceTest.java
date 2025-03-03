//package com.hae5.sfaas.preventive.service;
//
//import com.hae5.sfaas.SfaasApplicationTests;
//import com.hae5.sfaas.preventive.dto.response.PreventiveMaintenanceResponse;
//import com.hae5.sfaas.preventive.mapper.PreventiveMaintenanceMapper;
//import com.hae5.sfaas.preventive.model.PreventiveMaintenance;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class PreventiveMaintenanceServiceTest extends SfaasApplicationTests {
//
//    @Autowired
//    private PreventiveMaintenanceService preventiveMaintenanceService;
//
//    @Autowired
//    private PreventiveMaintenanceMapper preventiveMaintenanceMapper;
//
//    @BeforeEach
//    void setUp() {
//        preventiveMaintenanceMapper.deleteAll();
//    }
//
//    @AfterEach
//    void cleanup() {
//        preventiveMaintenanceMapper.deleteAll();
//    }
//
//    @Test
//    @DisplayName("결과가 정상이 아닌 Schedule 조회 테스트")
//    void getAllNotCompletedPreventiveTask() {
//        // given
//        PreventiveMaintenance p1 = PreventiveMaintenance.create(
//                1,
//                "testequip1",
//                "line1",
//                "process1",
//                "serial1",
//                LocalDateTime.of(2024, 12, 12, 0, 0),
//                LocalDateTime.of(2024, 12, 12, 0, 0),
//                "비정상",
//                1,
//                "진행중",
//                LocalDateTime.of(2024, 12, 13, 0, 0));
//
//        preventiveMaintenanceMapper.save(p1);
//
//        // when
//        List<PreventiveMaintenanceResponse> result = preventiveMaintenanceService.getPreventiveMaintenance();
//
//        // then
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0).getLineId()).isEqualTo("line1");
//        assertThat(result.get(0).getProcessId()).isEqualTo("process1");
//        assertThat(result.get(0).getSerialNumber()).isEqualTo("serial1");
//        assertThat(result.get(0).getPlannedDate()).isEqualTo(LocalDateTime.of(2024, 12, 12, 0, 0));
//        assertThat(result.get(0).getEstimatedTime()).isEqualTo(1);
//    }
//
//    @Test
//    @DisplayName("결과가 정상이 아닌 Schedule이 없을 경우 빈 리스트 반환 테스트")
//    void getAllNotCompletedPreventiveTask_ReturnsEmptyList() {
//        // given
//        PreventiveMaintenance p1 = PreventiveMaintenance.create(
//                1,
//                "testequip1",
//                "line1",
//                "process1",
//                "serial1",
//                LocalDateTime.of(2024, 12, 12, 0, 0),
//                LocalDateTime.of(2024, 12, 12, 0, 0),
//                "정상",
//                1,
//                "완료",
//                LocalDateTime.of(2024, 12, 13, 0, 0));
//
//        preventiveMaintenanceMapper.save(p1);
//
//        // when
//        List<PreventiveMaintenanceResponse> result = preventiveMaintenanceService.getPreventiveMaintenance();
//
//        // then
//        assertThat(result).isNotNull();
//        assertThat(result).isEmpty();
//    }
//}
