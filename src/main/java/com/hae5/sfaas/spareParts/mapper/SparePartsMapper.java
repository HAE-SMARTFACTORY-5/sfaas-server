package com.hae5.sfaas.spareParts.mapper;

import com.hae5.sfaas.spareParts.model.SpareParts;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface SparePartsMapper {
    void save(SpareParts spareParts);

    void deleteAll();

    List<SpareParts> findAll();

    Optional<SpareParts> findByItemCode(String itemCode);

    List<SpareParts> findByFactoryId(Long factoryId);
}
