package com.sparta.personalproject2.service;

import com.sparta.personalproject2.dto.CommentRequestDto;
import com.sparta.personalproject2.dto.CommentResponseDto;
import com.sparta.personalproject2.entity.Comment;
import com.sparta.personalproject2.entity.Schedule;
import com.sparta.personalproject2.repository.CommentRepository;
import com.sparta.personalproject2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    private Comment findCommentId(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID를 찾을 수 없습니다.")
        );
    }

    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto) {
        Schedule schedule = findId(id);
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
        schedule.addComment(comment);
        scheduleRepository.save(schedule);
        return new CommentResponseDto(comment);

    }

    public Comment getPersonalComment(Long id) {
        Comment comment = findCommentId(id);
        return comment;

    }

    public List<CommentResponseDto> getAllComment() {
        return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
    }

    public Comment deleteComment(Long id) {
        Comment comment = findCommentId(id);
        commentRepository.delete(comment);
        return comment;
    }

    private Schedule findId(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID를 찾을 수 없습니다.")
        );
    }

}
