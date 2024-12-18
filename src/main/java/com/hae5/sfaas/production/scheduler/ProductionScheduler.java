package com.hae5.sfaas.production.scheduler;

import com.hae5.sfaas.production.mapper.ProductProcessStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductionScheduler {

    private final ProductProcessStatusMapper productProcessStatusMapper;

    @Scheduled(fixedDelay = 10000) // 프레스 -> 차체
    public void updateProductProcessStatusSequenceZero() {
        productProcessStatusMapper.updateSequence(0, 1);
    }

    @Scheduled(fixedDelay = 10000) // 차체 -> 도장
    public void updateProductProcessStatusSequenceOne() {
        productProcessStatusMapper.updateSequence(1, 2);
    }

    @Scheduled(fixedDelay = 10000) // 도장 -> 의장
    public void updateProductProcessStatusSequenceTwo() {
        productProcessStatusMapper.updateSequence(2, 3);
    }

    @Scheduled(fixedDelay = 10000) // 도장 -> 검수
    public void updateProductProcessStatusSequenceThree() {
        productProcessStatusMapper.updateSequence(3, 4);
    }

}
