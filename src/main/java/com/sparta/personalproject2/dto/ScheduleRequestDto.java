package com.sparta.personalproject2.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private Long userId;
    private String nameId;
    private String scheduleTitle;
    private String schedule;
}
