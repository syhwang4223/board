package com.syhwang.board.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CommentDto {

    @NotBlank
    private String content;
    @NotNull
    private String writer;

    @Builder
    public CommentDto(String content, String writer){
        this.content = content;
        this.writer = writer;
    }

}
