package com.hae5.sfaas.alerts.mapper;

import com.hae5.sfaas.alerts.model.Alerts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AlertsMapper {
    List<Alerts> findAll();

    Optional<Alerts> findById(String alarmId);

    void save(Alerts alerts);

    void deleteAll();

    List<Alerts> findAllByLineId(String lineId);
}
