package com.hae5.sfaas.maintenance.schedule.service;

import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.maintenance.schedule.dto.response.ScheduleResponse;
import com.hae5.sfaas.maintenance.schedule.mapper.ScheduleMapper;
import com.hae5.sfaas.maintenance.schedule.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hae5.sfaas.common.exception.ExceptionCode.SCHDUEL_NOT_FOUND_ERROR;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleMapper scheduleMapper;

    @Transactional(readOnly = true)
    public List<ScheduleResponse> getSchedule(){
        return scheduleMapper.findAll().stream()
                .map(ScheduleResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public ScheduleResponse getScheduleById(Integer id) {
        Schedule schedule = scheduleMapper.findById(id).orElseThrow(
                () -> SfaasException.create(SCHDUEL_NOT_FOUND_ERROR));
        return ScheduleResponse.from(schedule);

    }
}
