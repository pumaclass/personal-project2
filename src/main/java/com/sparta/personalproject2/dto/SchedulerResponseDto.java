package com.sparta.personalproject2.dto;

import com.sparta.personalproject2.entity.Schedule;
import lombok.Getter;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String name;
    private String scheduleTitle;
    private String schedule;
    private int commentCnt;

    public SchedulerResponseDto(Schedule Schedule) {
        this.id = Schedule.getId();
        this.name = Schedule.getName();
        this.scheduleTitle = Schedule.getScheduleTitle();
        this.schedule = Schedule.getSchedule();
//        this.commentList = Schedule.getCommentEntityList();
        this.commentCnt = Schedule.getCommentCnt();
    }

    public SchedulerResponseDto(Long id, String name, String scheduleTitle, String schedule) {
        this.id = id;
        this.name = name;
        this.scheduleTitle = scheduleTitle;
        this.schedule = schedule;
    }
}
