package com.syhwang.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentDto {

    private String content;
    private String writer;

    @Builder
    public CommentDto(String content, String writer){
        this.content = content;
        this.writer = writer;
    }

}
