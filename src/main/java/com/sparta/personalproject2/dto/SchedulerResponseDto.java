package com.sparta.personalproject2.dto;

import com.sparta.personalproject2.entity.ScheduleEntity;
import lombok.Getter;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String name;
    private String scheduleTitle;
    private String schedule;

    public SchedulerResponseDto(ScheduleEntity saveSchedule) {
        this.id = saveSchedule.getId();
        this.name = saveSchedule.getName();
        this.scheduleTitle = saveSchedule.getScheduleTitle();
        this.schedule = saveSchedule.getSchedule();
    }
}
