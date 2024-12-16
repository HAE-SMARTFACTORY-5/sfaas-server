package com.hae5.sfaas.line.mapper;

import com.hae5.sfaas.line.model.LineOperationRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LineOperationRateMapper {
    List<LineOperationRate> getQuarterLineOperationRate(Long factoryId, int year);
}
