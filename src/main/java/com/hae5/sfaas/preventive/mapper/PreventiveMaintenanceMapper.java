package com.hae5.sfaas.preventive.mapper;

import com.hae5.sfaas.preventive.dto.response.PreventiveMaintenanceResponse;
import com.hae5.sfaas.preventive.model.PreventiveMaintenance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PreventiveMaintenanceMapper {
    void save(PreventiveMaintenance preventiveMaintenance);
    void deleteAll();
    List<PreventiveMaintenance> findAll();
    Optional<PreventiveMaintenance> findById(Integer maintenanceId);
}
