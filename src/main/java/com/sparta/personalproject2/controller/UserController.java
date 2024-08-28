package com.sparta.personalproject2.controller;

import com.sparta.personalproject2.dto.UserRequestDto;
import com.sparta.personalproject2.dto.UserResponseDto;
import com.sparta.personalproject2.entity.Schedule;
import com.sparta.personalproject2.entity.User;
import com.sparta.personalproject2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(("/user"))
    ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto){
        return userService.createUser(requestDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody  UserRequestDto requestDto){
        return ResponseEntity.ok(userService.updateUser(id, requestDto));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
