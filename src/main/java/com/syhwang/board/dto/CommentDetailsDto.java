package com.syhwang.board.dto;

import com.syhwang.board.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class CommentDetailsDto {

    private Long commentId;
    @NotBlank
    private String content;
    private Long writerId;
    private Long postId;
    @NotNull
    private String writer;
    private LocalDateTime writeDateTime;

    public CommentDetailsDto(Comment comment){
        commentId = comment.getId();
        content = comment.getContent();
        postId = comment.getPost().getId();
        writerId = comment.getWriter().getId();
        writer = comment.getWriter().getNickname();
        writeDateTime = comment.getWriteDateTime();
    }

}
