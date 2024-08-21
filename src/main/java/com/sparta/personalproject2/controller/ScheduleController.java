package com.sparta.personalproject2.controller;

import com.sparta.personalproject2.dto.ScheduleRequestDto;
import com.sparta.personalproject2.dto.SchedulerResponseDto;
import com.sparta.personalproject2.entity.ScheduleEntity;
import com.sparta.personalproject2.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    ScheduleController(ScheduleService schedulerService){
        this.scheduleService = schedulerService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<SchedulerResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return ResponseEntity.ok(scheduleService.createSchedule(requestDto));
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleEntity> getPersonalSchedule(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.getPersonalSchedule(id));
    }

    @PutMapping("/schedule/{id}")
    public ResponseEntity<ScheduleEntity> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto));
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<ScheduleEntity> deleteSchedule(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.deleteSchedule(id));
    }

}
