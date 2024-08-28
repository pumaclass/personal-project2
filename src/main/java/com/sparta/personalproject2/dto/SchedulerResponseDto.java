package com.sparta.personalproject2.dto;

import com.sparta.personalproject2.entity.Schedule;
import com.sparta.personalproject2.entity.User;
import com.sparta.personalproject2.entity.UserSchedule;
import lombok.Getter;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private Long userId;
    private String scheduleTitle;
    private String schedule;
    private int commentCnt;

    public SchedulerResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUser() != null ? schedule.getUser().getId() : null;
        this.scheduleTitle = schedule.getScheduleTitle();
        this.schedule = schedule.getSchedule();
        this.commentCnt = schedule.getCommentCnt();

    }

    public SchedulerResponseDto(Schedule saveSchedule, User user) {
        this.id = saveSchedule.getId();
        this.scheduleTitle = saveSchedule.getScheduleTitle();
        this.schedule = saveSchedule.getSchedule();
        this.userId = user.getId();
        this.commentCnt = this.getCommentCnt();
    }
}
