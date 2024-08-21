package com.sparta.personalproject2.controller;

import com.sparta.personalproject2.dto.CommentRequestDto;
import com.sparta.personalproject2.dto.CommentResponseDto;
import com.sparta.personalproject2.entity.CommentEntity;
import com.sparta.personalproject2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule/comment/{id}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        return ResponseEntity.ok(scheduleService.createComment(id, requestDto));
    }

    @GetMapping("/schedule/comment/{id}")
    public ResponseEntity<CommentEntity> getPersonalComment(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.getPersonalComment(id));
    }

    @GetMapping("/schedule/comment")
    public List<CommentResponseDto> getAllComment(){
        return scheduleService.getAllComment();
    }

    @DeleteMapping("/schedule/comment/{id}")
    ResponseEntity<CommentEntity> deleteComment(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.deleteComment(id));
    }
}
