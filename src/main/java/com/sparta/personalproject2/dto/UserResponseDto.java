package com.sparta.personalproject2.dto;

import com.sparta.personalproject2.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String userName;
    private String email;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }
}
