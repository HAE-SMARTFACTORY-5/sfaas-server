package com.hae5.sfaas.line.mapper;

import com.hae5.sfaas.line.dto.response.TotalLineOperationResponse;
import com.hae5.sfaas.line.model.LineOperationRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LineOperationRateMapper {
    void save(LineOperationRate lineOperationRate);
    void deleteAll();
    List<LineOperationRate> findAll();
    List<LineOperationRate> getNowYearLineOperationRate(Long factoryId, int year);
    List<LineOperationRate> getNowYearLineOperation(Long factoryId, int year);
}
