package com.hae5.sfaas.production.mapper;

import com.hae5.sfaas.production.model.ProductProcessStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProductProcessStatusMapper {
    ProductProcessStatus findByProductId(String productId);

    void updateSequence();

    List<ProductProcessStatus> findAll();

    void save(ProductProcessStatus productProcessStatus);
}
