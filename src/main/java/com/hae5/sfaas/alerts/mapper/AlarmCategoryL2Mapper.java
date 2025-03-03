package com.hae5.sfaas.alerts.mapper;

import com.hae5.sfaas.alerts.model.AlarmCategoryL2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlarmCategoryL2Mapper {
    List<AlarmCategoryL2> findAll();
}
