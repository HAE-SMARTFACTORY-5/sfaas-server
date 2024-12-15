package com.hae5.sfaas.production.mapper;

import com.hae5.sfaas.production.dto.response.ProductionPerformanceResponse;
import com.hae5.sfaas.production.model.ProductionPerformance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductionPerformanceMapper {
    void save(ProductionPerformance productionPerformance);
    void deleteAll();
    List<ProductionPerformanceResponse> findAll();
    List<ProductionPerformanceResponse> getTodayPerformance();

}
