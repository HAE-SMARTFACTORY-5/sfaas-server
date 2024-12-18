package com.hae5.sfaas.production.scheduler;

import com.hae5.sfaas.production.mapper.ProductProcessStatusMapper;
import com.hae5.sfaas.production.model.ProductProcessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductionScheduler {

    private final ProductProcessStatusMapper productProcessStatusMapper;

    @Scheduled(fixedDelay = 10000) // 프레스 -> 차체
    public void updateProductProcessStatusSequence() {
        productProcessStatusMapper.updateSequence();
    }

}
