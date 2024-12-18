package com.hae5.sfaas.production.service;

import com.hae5.sfaas.production.mapper.ProductProcessStatusMapper;
import com.hae5.sfaas.production.model.ProductProcessStatus;
import com.hae5.sfaas.spareParts.dto.response.SparePartsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductProcessStatusService {

    private final ProductProcessStatusMapper productProcessStatusMapper;

    @Transactional(readOnly = true)
    public SparePartsResponse getProductProcessStatus(String productId) {
        ProductProcessStatus productProcessStatus = productProcessStatusMapper.findByProductId(productId);
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(0);
        result.add(0);
        result.add(0);
        result.add(0);
        result.set(productProcessStatus.getSequence(), 1);
        return SparePartsResponse.of(result, productId);
    }
}
