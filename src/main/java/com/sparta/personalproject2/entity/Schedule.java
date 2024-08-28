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
    @Column(name = "schedule_title", nullable = false)
    private String scheduleTitle;
    @Column(name = "schedule", nullable = false)
    private String schedule;
    @Column(name = "commentCnt")
    private int commentCnt;


    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> commentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserSchedule> userScheduleList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Schedule(ScheduleRequestDto requestDto){
        this.id = requestDto.getId();
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.schedule = requestDto.getSchedule();
    }

    public void addComment(Comment comment){
        this.commentEntityList.add(comment);
        comment.setSchedule(this);
    }

    public void update(ScheduleRequestDto requestDto){
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.schedule = requestDto.getSchedule();
    }


    public void addUserScheduleList(UserSchedule userSchedule) {
        this.userScheduleList.add(userSchedule);
        userSchedule.setSchedule(this);
    }

    public Schedule(ScheduleRequestDto requestDto, User user) {
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.schedule = requestDto.getSchedule();
        this.user = user;
    }
}
