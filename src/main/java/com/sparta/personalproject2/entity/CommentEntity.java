package com.sparta.personalproject2.entity;

import com.sparta.personalproject2.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

    public CommentEntity(CommentRequestDto requestDto){
        this.id = requestDto.getId();
        this.name = requestDto.getName();
        this.comment = requestDto.getComment();
    }

}
