package com.hae5.sfaas.spareParts.service;

import com.hae5.sfaas.spareParts.dto.response.SparePartsResponse;
import com.hae5.sfaas.spareParts.mapper.SparePartsMapper;
import com.hae5.sfaas.spareParts.model.SpareParts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SparePartsService {

    private final SparePartsMapper sparePartsMapper;

    public SparePartsResponse getSpareParts() {
        List<SpareParts> allSpareParts = sparePartsMapper.findAll();
        return SparePartsResponse.from(allSpareParts);
    }
}
