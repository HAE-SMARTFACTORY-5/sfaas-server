package com.hae5.sfaas.monthly.failure.mapper;

import com.hae5.sfaas.monthly.failure.model.MonthlyFailure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonthlyFailureMapper {
    void save(MonthlyFailure failure);

    void deleteAll();

    List<MonthlyFailure> findAll();

    List<MonthlyFailure> findByProcess(String process);

}
