package com.hae5.sfaas.maintenance.schedule.mapper;

import com.hae5.sfaas.maintenance.schedule.model.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ScheduleMapper {
    List<Schedule> findAll();

    Optional<Schedule> findById(Integer id);

    void deleteAll();

    void save(Schedule schedule1);
}
