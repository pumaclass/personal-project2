package com.sparta.personalproject2.repository;

import com.sparta.personalproject2.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScheduleRepositoy extends JpaRepository<UserSchedule, Long> {
}
