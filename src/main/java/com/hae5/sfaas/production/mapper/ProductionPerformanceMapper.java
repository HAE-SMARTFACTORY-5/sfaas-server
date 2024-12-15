package com.hae5.sfaas.production.mapper;

import com.hae5.sfaas.production.dto.response.ProcessPerformance;
import com.hae5.sfaas.production.model.ProductionPerformance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductionPerformanceMapper {
    List<ProcessPerformance> getTodayPerformance();

}
