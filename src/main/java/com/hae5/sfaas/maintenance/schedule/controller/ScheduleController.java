package com.hae5.sfaas.maintenance.schedule.controller;

import com.hae5.sfaas.maintenance.schedule.dto.response.ScheduleResponse;
import com.hae5.sfaas.maintenance.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/maintenance/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getSchedule(){
        List<ScheduleResponse> responses = scheduleService.getSchedule();
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable Integer id){
        ScheduleResponse response = scheduleService.getScheduleById(id);
        return ResponseEntity.ok().body(response);
    }

}
