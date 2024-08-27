package com.sparta.personalproject2.service;

import com.sparta.personalproject2.dto.CommentRequestDto;
import com.sparta.personalproject2.dto.CommentResponseDto;
import com.sparta.personalproject2.dto.ScheduleRequestDto;
import com.sparta.personalproject2.dto.SchedulerResponseDto;
import com.sparta.personalproject2.entity.Comment;
import com.sparta.personalproject2.entity.Schedule;
import com.sparta.personalproject2.repository.CommentRepository;
import com.sparta.personalproject2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public SchedulerResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule saveSchedule = new Schedule(requestDto);
        scheduleRepository.save(saveSchedule);
        SchedulerResponseDto responseDto = new SchedulerResponseDto(saveSchedule);
        return responseDto;
    }

    public Schedule getPersonalSchedule(Long id) {
        Schedule schedule = findId(id);
        return schedule;
    }

    @Transactional
    public Schedule updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findId(id);
        schedule.update(requestDto);
        return schedule;

    }

    public Schedule deleteSchedule(Long id) {
        Schedule schedule = findId(id);
        scheduleRepository.delete(schedule);
        return schedule;
    }

    private Schedule findId(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID를 찾을 수 없습니다.")
        );
    }

    public Page<SchedulerResponseDto> getAllSchedule(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Schedule> scheduleList;
        scheduleList = scheduleRepository.findAll(pageable);
        for(Schedule schedule:scheduleList){
            schedule.setCommentCnt(schedule.getCommentEntityList().size());
//        SchedulerResponseDto responseDto = new SchedulerResponseDto(schedule.getId(), schedule.getName(), schedule.getScheduleTitle(), schedule.getSchedule(), commentCount);
//        responseDtoList.add(responseDto);
        }
        return scheduleList.map(SchedulerResponseDto::new);
//        return scheduleRepository.findAll(pageable)
//                stream().map(SchedulerResponseDto::new).toList();
    }


}
