package com.sparta.personalproject2.dto;

import com.sparta.personalproject2.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String name;
    private String comment;

    public CommentResponseDto(Comment saveComment){
        this.id = saveComment.getId();
        this.name = saveComment.getName();
        this.comment = saveComment.getComment();

    }
}
