package com.sparta.personalproject2.dto;

import com.sparta.personalproject2.entity.CommentEntity;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String name;
    private String comment;

    public CommentResponseDto(CommentEntity saveComment){
        this.id = saveComment.getId();
        this.name = saveComment.getName();
        this.comment = saveComment.getComment();

    }
}
