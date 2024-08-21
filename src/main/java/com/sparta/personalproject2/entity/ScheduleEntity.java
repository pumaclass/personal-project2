package com.sparta.personalproject2.entity;

import com.sparta.personalproject2.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class ScheduleEntity extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "schedule_title", nullable = false)
    private String scheduleTitle;
    @Column(name = "schedule", nullable = false)
    private String schedule;

    public ScheduleEntity(ScheduleRequestDto requestDto){
        this.id = requestDto.getId();
        this.name = requestDto.getName();
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.schedule = requestDto.getSchedule();
    }

    public void update(ScheduleRequestDto requestDto){
        this.name = requestDto.getName();
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.schedule = requestDto.getSchedule();
    }

}
