package com.hae5.sfaas.alerts.mapper;

import com.hae5.sfaas.alerts.model.AlertAction;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface AlertActionMapper {
    void save(AlertAction alertAction);

    Optional<AlertAction> findById(String alarmId);

    List<AlertAction> findAll();

    void deleteAll();
}
