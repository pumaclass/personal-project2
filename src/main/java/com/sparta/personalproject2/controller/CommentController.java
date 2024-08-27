package com.sparta.personalproject2.controller;

import com.sparta.personalproject2.dto.CommentRequestDto;
import com.sparta.personalproject2.dto.CommentResponseDto;
import com.sparta.personalproject2.entity.Comment;
import com.sparta.personalproject2.service.CommentService;
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
    private final CommentService commentService;

    @PostMapping("/schedule/comment/{id}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        return ResponseEntity.ok(commentService.createComment(id, requestDto));
    }

    @GetMapping("/schedule/comment/{id}")
    public ResponseEntity<Comment> getPersonalComment(@PathVariable Long id){
        return ResponseEntity.ok(commentService.getPersonalComment(id));
    }

    @GetMapping("/schedule/comment")
    public List<CommentResponseDto> getAllComment(){
        return commentService.getAllComment();
    }

    @DeleteMapping("/schedule/comment/{id}")
    ResponseEntity<Comment> deleteComment(@PathVariable Long id){
        return ResponseEntity.ok(commentService.deleteComment(id));
    }
}
