package com.sparta.personalproject2.service;

import com.sparta.personalproject2.dto.CommentRequestDto;
import com.sparta.personalproject2.dto.CommentResponseDto;
import com.sparta.personalproject2.dto.ScheduleRequestDto;
import com.sparta.personalproject2.dto.SchedulerResponseDto;
import com.sparta.personalproject2.entity.CommentEntity;
import com.sparta.personalproject2.entity.ScheduleEntity;
import com.sparta.personalproject2.repository.CommentRepository;
import com.sparta.personalproject2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public SchedulerResponseDto createSchedule(ScheduleRequestDto requestDto) {
        ScheduleEntity saveSchedule = new ScheduleEntity(requestDto);
        scheduleRepository.save(saveSchedule);
        SchedulerResponseDto responseDto = new SchedulerResponseDto(saveSchedule);
        return responseDto;
    }

    public ScheduleEntity getPersonalSchedule(Long id) {
        ScheduleEntity schedule = findId(id);
        return schedule;
    }

    @Transactional
    public ScheduleEntity updateSchedule(Long id, ScheduleRequestDto requestDto) {
        ScheduleEntity schedule = findId(id);
        schedule.update(requestDto);
        return schedule;

    }

    public ScheduleEntity deleteSchedule(Long id) {
        ScheduleEntity schedule = findId(id);
        scheduleRepository.delete(schedule);
        return schedule;
    }

    private ScheduleEntity findId(Long id){
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID를 찾을 수 없습니다.")
        );
    }

    private CommentEntity findCommentId(Long id){
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID를 찾을 수 없습니다.")
        );
    }

    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto) {
        ScheduleEntity schedule = findId(id);
        CommentEntity comment = new CommentEntity(requestDto);
        commentRepository.save(comment);
        schedule.addComment(comment);
        scheduleRepository.save(schedule);
        return new CommentResponseDto(comment);

    }

    public CommentEntity getPersonalComment(Long id) {
        CommentEntity comment = findCommentId(id);
        return comment;

    }

    public List<CommentResponseDto> getAllComment() {
        return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
    }

    public CommentEntity deleteComment(Long id) {
        CommentEntity comment = findCommentId(id);
        commentRepository.delete(comment);
        return comment;
    }
}
