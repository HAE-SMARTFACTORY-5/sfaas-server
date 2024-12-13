package com.hae5.sfaas.fault.mapper;

import com.hae5.sfaas.fault.model.Fault;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FaultMapper {
    List<Fault> findAll();

    Optional<Fault> findById(Long faultId);
}
