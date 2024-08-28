package com.sparta.personalproject2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.personalproject2.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserSchedule> userScheduleList = new ArrayList<>();

    public void update(UserRequestDto requestDto) {
        this.id = requestDto.getId();
        this.userName = requestDto.getUserName();;
        this.email = requestDto.getEmail();
    }

    public User(UserRequestDto requestDto){
        this.id = requestDto.getId();
        this.userName = requestDto.getUserName();
        this.email = requestDto.getEmail();
    }

    public void addUserScheduleList(UserSchedule userSchedule) {
        this.userScheduleList.add(userSchedule);
        userSchedule.setUser(this);

    }
}
