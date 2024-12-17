package com.hae5.sfaas.alerts.mapper;

import com.hae5.sfaas.alerts.model.AlertsRetrieval;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AlertsRetrievalMapper {
    List<AlertsRetrieval> findAll();

    List<AlertsRetrieval> findByAlarmId(@Param("alarmId") String alarmId);

    List<AlertsRetrieval> findByDateRange(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    List<AlertsRetrieval> findByLineAndProcess(@Param("lineId") String lineId,
            @Param("processId") String processId);
}
