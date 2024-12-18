package com.hae5.sfaas.production.mapper;

import com.hae5.sfaas.production.model.ProductProcessStatus;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProductProcessStatusMapper {
    ProductProcessStatus findByProductId(String productId);

    void updateSequence(int nowSequence, int nextSequence);
}
