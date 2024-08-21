package com.sparta.personalproject2.service;

import com.sparta.personalproject2.dto.ScheduleRequestDto;
import com.sparta.personalproject2.dto.SchedulerResponseDto;
import com.sparta.personalproject2.entity.ScheduleEntity;
import com.sparta.personalproject2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public SchedulerResponseDto createSchedule(ScheduleRequestDto requestDto) {
        ScheduleEntity saveSchedule = new ScheduleEntity(requestDto);
        scheduleRepository.save(saveSchedule);
        SchedulerResponseDto responseDto = new SchedulerResponseDto(saveSchedule);
        return responseDto;
    }

    public ScheduleEntity getPersonalSchedule(Long id) {
        ScheduleEntity schedule = findId(id);
        return schedule;
    }

    @Transactional
    public ScheduleEntity updateSchedule(Long id, ScheduleRequestDto requestDto) {
        ScheduleEntity schedule = findId(id);
        schedule.update(requestDto);
        return schedule;

    }

    public ScheduleEntity deleteSchedule(Long id) {
        ScheduleEntity schedule = findId(id);
        scheduleRepository.delete(schedule);
        return schedule;
    }

    private ScheduleEntity findId(Long id){
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID를 찾을 수 없습니다.")
        );
    }
}
