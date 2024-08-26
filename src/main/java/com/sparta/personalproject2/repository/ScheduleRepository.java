package com.sparta.personalproject2.repository;

import com.sparta.personalproject2.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    //댓글 카운트
    @Query("select count(*) from CommentEntity")
    Long commentCnt();
//스케줄 id에 묶여있는 comment의 갯수를 카운트해서 가져와야돼

}
