package com.hae5.sfaas.alerts.mapper;

import com.hae5.sfaas.alerts.model.AlarmHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlarmHistoryMapper {
    void save(AlarmHistory alarmHistory);
    void deleteAll();
    List<AlarmHistory> findAll();
    List<AlarmHistory> getNowYearTotalAlarmHistory(int year);
}