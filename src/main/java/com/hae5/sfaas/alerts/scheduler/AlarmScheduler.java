package com.hae5.sfaas.alerts.scheduler;

import com.hae5.sfaas.alerts.mapper.AlarmCategoryL2Mapper;
import com.hae5.sfaas.alerts.mapper.AlertsMapper;
import com.hae5.sfaas.alerts.model.AlarmCategoryL2;
import com.hae5.sfaas.alerts.model.Alerts;
import com.hae5.sfaas.process.mapper.ProcessMapper;
import com.hae5.sfaas.process.model.Process;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AlarmScheduler {

    private final AlertsMapper alertsMapper;
    private final ProcessMapper processMapper;
    private final AlarmCategoryL2Mapper alarmCategoryL2Mapper;

    @Scheduled(fixedDelay = 10000)
    public void insertAlarmSchedule() {
        String lineId = "G_L"; // 1개로 운영중
        List<Process> processes = processMapper.findAll();
        List<AlarmCategoryL2> alarmCategoryL2s = alarmCategoryL2Mapper.findAll();
        if (!processes.isEmpty() && !alarmCategoryL2s.isEmpty()) {
            Collections.shuffle(processes);
            Collections.shuffle(alarmCategoryL2s);
            alertsMapper.save(Alerts.create(lineId, processes.get(0).getProcessId(), alarmCategoryL2s.get(0).getCategoryL2Id(), LocalDateTime.now()));
        }
    }

}
