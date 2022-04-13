package com.syhwang.board.dto;

import com.syhwang.board.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class CommentDetailsDto {

    @NotBlank
    private String content;
    @NotNull
    private String writer;
    private LocalDateTime writeDateTime;

    public CommentDetailsDto(Comment comment){
        content = comment.getContent();
        writer = comment.getWriter().getLoginId();
        writeDateTime = comment.getWriteDateTime();
    }

}
