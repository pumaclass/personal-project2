package com.sparta.personalproject2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.personalproject2.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "schedule")
    private ScheduleEntity schedule;

    public CommentEntity(CommentRequestDto requestDto){
        this.id = requestDto.getId();
        this.name = requestDto.getName();
        this.comment = requestDto.getComment();
    }

}
