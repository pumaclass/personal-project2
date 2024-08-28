package com.sparta.personalproject2.service;

import com.sparta.personalproject2.dto.*;
import com.sparta.personalproject2.entity.Comment;
import com.sparta.personalproject2.entity.Schedule;
import com.sparta.personalproject2.entity.User;
import com.sparta.personalproject2.entity.UserSchedule;
import com.sparta.personalproject2.repository.CommentRepository;
import com.sparta.personalproject2.repository.ScheduleRepository;
import com.sparta.personalproject2.repository.UserRepository;
import com.sparta.personalproject2.repository.UserScheduleRepositoy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final UserScheduleRepositoy userScheduleRepositoy;

    public SchedulerResponseDto createSchedule(Long userId, ScheduleRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("ID를 찾을 수 없습니다."));
        Schedule schedule = new Schedule(requestDto, user);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        UserSchedule userSchedule = new UserSchedule(user, savedSchedule);
        userScheduleRepositoy.save(userSchedule);

        return new SchedulerResponseDto(savedSchedule);
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

//    public Page<SchedulerResponseDto> getAllSchedule(int page, int size, String sortBy, boolean isAsc) {
//        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable pageable = PageRequest.of(page,size,sort);
//
//        Page<Schedule> scheduleList;
//
//        scheduleList = scheduleRepository.findAll(pageable);
//        UserSchedule userSchedule = new UserSchedule();
//        for(Schedule schedule:scheduleList){
//            schedule.setCommentCnt(schedule.getCommentEntityList().size());
//
//        }
//        return scheduleList.map(SchedulerResponseDto::new);
//    }


    public Page<SchedulerResponseDto> getAllSchedule(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Schedule> scheduleList = scheduleRepository.findAll(pageable);

        return scheduleList.map(schedule -> {
            schedule.setCommentCnt(schedule.getCommentEntityList().size());
            return new SchedulerResponseDto(schedule);
        });
    }

//    public UserScheduleResponseDto addUserInSchedule(Long scheduleId, Long userId, Long addUserId) {
//        Schedule schedule = findId(scheduleId);
////        if (schedule.getUser().getId()== userId) {
//            // user를 userId로 찾은 뒤 user에 저장
//            User user = userRepository.findById(addUserId).orElseThrow(() -> new IllegalArgumentException("찾는 id가 존재하지 않습니다."));
//            // userschedule과 User, Schedule에 각각 저장하기
//            UserSchedule userSchedule = new UserSchedule(user, schedule);
//            userScheduleRepositoy.save(userSchedule);
//            user.addUserScheduleList(userSchedule);
//
//            // schedule에도 추가해주기
//            schedule.addUserScheduleList(userSchedule);
//
//            List<User> userList = new ArrayList<>();
//            for (UserSchedule us : schedule.getUserScheduleList()) {
//                userList.add(us.getUser());
//            }
//
//            UserScheduleResponseDto userScheduleResponseDto = new UserScheduleResponseDto(schedule, userList);
//            return userScheduleResponseDto;
////        } else {
////            log.error("잘못된 입력입니다.");
////
////            return null;
////        }
//    }

    public UserScheduleResponseDto addUserInSchedule(Long scheduleId, Long userId, Long addUserId) {
        Schedule schedule = findId(scheduleId);
        User userToAdd = userRepository.findById(addUserId)
                .orElseThrow(() -> new IllegalArgumentException("추가할 사용자를 찾을 수 없습니다."));

        // 이미 스케줄에 사용자가 존재하는지 확인
        boolean userAlreadyExists = schedule.getUserScheduleList().stream()
                .anyMatch(userSchedule -> userSchedule.getUser().getId().equals(addUserId));

        if (userAlreadyExists) {
            throw new IllegalStateException("이미 스케줄에 존재하는 사용자입니다.");
        }

        UserSchedule userSchedule = new UserSchedule(userToAdd, schedule);
        userScheduleRepositoy.save(userSchedule);
        userToAdd.addUserScheduleList(userSchedule);
        schedule.addUserScheduleList(userSchedule);

        List<User> userList = schedule.getUserScheduleList().stream()
                .map(UserSchedule::getUser)
                .collect(Collectors.toList());

        return new UserScheduleResponseDto(schedule, userList);
    }
}
