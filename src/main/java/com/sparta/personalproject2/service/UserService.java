package com.sparta.personalproject2.service;

import com.sparta.personalproject2.dto.UserRequestDto;
import com.sparta.personalproject2.dto.UserResponseDto;
import com.sparta.personalproject2.entity.User;
import com.sparta.personalproject2.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserResponseDto> createUser(UserRequestDto requestDto) {
        User user = new User(requestDto);
        userRepository.save(user);
        UserResponseDto responseDto = new UserResponseDto(user);
        return ResponseEntity.ok(responseDto);
    }

    public User getUser(Long id) {
        User user = findId(id);
        return user;
    }

    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    public User updateUser(Long id, UserRequestDto requestDto) {
        User user = findId(id);
        user.update(requestDto);
        return user;
    }

    public User deleteUser(Long id) {
        User user = findId(id);
        userRepository.delete(user);
        return user;
    }

    private User findId(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID를 찾을 수 없습니다.")
        );
    }
}
