package com.hae5.sfaas.alerts.mapper;

import com.hae5.sfaas.alerts.model.AlertAction;
import com.hae5.sfaas.alerts.model.AlertActionDetail;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface AlertActionMapper {
    void save(AlertAction alertAction);

    void saveActionDetail(AlertActionDetail detail);

    Optional<AlertAction> findByAlarmId(String alarmId);

    List<AlertActionDetail> findDetailsByAlarmId(String alarmId);

    void deleteAll();

    void deleteAllDetails();

    void deleteAllDetailsByAlarmId(String alarmId);

}
