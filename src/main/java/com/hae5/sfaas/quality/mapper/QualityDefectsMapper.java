package com.hae5.sfaas.quality.mapper;

import com.hae5.sfaas.quality.model.QualityDefects;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QualityDefectsMapper {
    void save(QualityDefects qualityDefects);
    void deleteAll();
    List<QualityDefects> findAll();
    List<QualityDefects> getOurQualityDefects(Long factoryId);
}
