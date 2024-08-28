package com.sparta.personalproject2.dto;

import com.sparta.personalproject2.entity.Schedule;
import com.sparta.personalproject2.entity.User;
import com.sparta.personalproject2.entity.UserSchedule;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserScheduleResponseDto {
    private Long scheduleId;
    private String todoTitle;
    private String todoSchedule;
    private int commentCnt;
    private List<Long> user;
    private List<UserResponseDto> users = new ArrayList<>();

    public UserScheduleResponseDto(Schedule schedule, List<User> userList) {
        this.scheduleId = schedule.getId();
        this.todoTitle = schedule.getScheduleTitle();
        this.todoSchedule = schedule.getSchedule();
        this.commentCnt = schedule.getCommentCnt();
//        this.user = schedule.getUserScheduleList().stream()
//                .map(userSchedule -> userSchedule)
        for(User userschedule: userList){
            UserResponseDto userResponseDto = new UserResponseDto(userschedule);
            users.add(userResponseDto);
        }



    }
}
