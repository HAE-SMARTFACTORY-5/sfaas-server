package com.hae5.sfaas.monthly.failure.service;

import com.hae5.sfaas.monthly.failure.dto.response.FailureRateResponse;
import com.hae5.sfaas.monthly.failure.mapper.MonthlyFailureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonthlyFailureService {
    private final MonthlyFailureMapper failureMapper;

    @Transactional(readOnly = true)
    public Map<String, List<FailureRateResponse>> getAllFailureRates() {
        return failureMapper.findAll().stream()
                .map(FailureRateResponse::from)
                .collect(Collectors.groupingBy(
                        FailureRateResponse::getProcess));
    }

    @Transactional(readOnly = true)
    public List<FailureRateResponse> getFailureRatesByProcess(String process) {
        return failureMapper.findByProcess(process).stream()
                .map(FailureRateResponse::from)
                .toList();
    }
}
