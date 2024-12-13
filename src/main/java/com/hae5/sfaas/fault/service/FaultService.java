package com.hae5.sfaas.fault.service;

import com.hae5.sfaas.fault.dto.response.FaultResponse;
import com.hae5.sfaas.fault.mapper.FaultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaultService {

    private final FaultMapper faultMapper;

    @Transactional(readOnly = true)
    public List<FaultResponse> getFault() {
        return faultMapper.findAll().stream()
                .map(FaultResponse::from)
                .toList();
    }
}
