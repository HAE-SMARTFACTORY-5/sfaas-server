package com.hae5.sfaas.production.service;

import com.hae5.sfaas.production.dto.response.ProductionPerformanceResponse;
import com.hae5.sfaas.production.mapper.ProductionPerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductionPerformanceService {

    private final ProductionPerformanceMapper productionPerformanceMapper;

    @Transactional(readOnly = true)
    public List<ProductionPerformanceResponse> getTodayPerformance() {
        return productionPerformanceMapper.getTodayPerformance();
    }
}
