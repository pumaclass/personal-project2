package com.sparta.personalproject2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.personalproject2.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "schedule_title", nullable = false)
    private String scheduleTitle;
    @Column(name = "schedule", nullable = false)
    private String schedule;
    @Column(name = "commentCnt")
    private int commentCnt;


    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> commentEntityList = new ArrayList<>();

    public Schedule(ScheduleRequestDto requestDto){
        this.id = requestDto.getId();
        this.name = requestDto.getName();
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.schedule = requestDto.getSchedule();
    }

    public void addComment(Comment comment){
        this.commentEntityList.add(comment);
        comment.setSchedule(this);
    }

    public void update(ScheduleRequestDto requestDto){
        this.name = requestDto.getName();
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.schedule = requestDto.getSchedule();
    }


}
