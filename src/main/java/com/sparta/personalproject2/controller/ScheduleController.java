package com.sparta.personalproject2.controller;

import com.sparta.personalproject2.dto.ScheduleRequestDto;
import com.sparta.personalproject2.dto.SchedulerResponseDto;
import com.sparta.personalproject2.dto.UserScheduleResponseDto;
import com.sparta.personalproject2.entity.Schedule;
import com.sparta.personalproject2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;


    @PostMapping("/schedule/{id}")
    public ResponseEntity<SchedulerResponseDto> createSchedule(@PathVariable Long id,@RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.createSchedule(id, requestDto));
    }

    @ResponseBody
    @GetMapping("/schedule")
    public ResponseEntity<Page<SchedulerResponseDto>> getAllSchedule(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") boolean isAsc) {

        return ResponseEntity.ok(scheduleService.getAllSchedule(page-1, size, sortBy, isAsc));
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<Schedule> getPersonalSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getPersonalSchedule(id));
    }
//    @PutMapping(/"schedule/{scheduleid}/{userid}/{adduserid}")


    @PutMapping("/schedule/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto));
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.deleteSchedule(id));
    }

    @PutMapping("/schedule/{scheduleId}/{userId}/{addUserId}")
    public ResponseEntity<UserScheduleResponseDto> addUserInSchedule(@PathVariable Long scheduleId,@PathVariable Long userId,@PathVariable Long addUserId){
        return ResponseEntity.ok(scheduleService.addUserInSchedule(scheduleId, userId, addUserId));
    }

}
