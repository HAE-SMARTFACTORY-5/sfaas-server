package com.hae5.sfaas.process.mapper;

import com.hae5.sfaas.process.model.Process;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessMapper {
    void save(Process process);

    void deleteAll();

    List<Process> findAll();
}
