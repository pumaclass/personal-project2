package com.sparta.personalproject2.repository;

import com.sparta.personalproject2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //댓글 카운트
    @Query("select count(*) from Comment")
    Long commentCnt();
//스케줄 id에 묶여있는 comment의 갯수를 카운트해서 가져와야돼

}
