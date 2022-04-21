package com.syhwang.board.dto;

import com.syhwang.board.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostDetailsDto {

    private final Long id;
    private final String title;
    private final String content;

    private final int commentCount;
    private final Long writerId;
    private final String writer;
    private final int views;
    private final LocalDateTime writeDateTime;

    public PostDetailsDto(Post post) {
        title = post.getTitle();
        writerId = post.getWriter().getId();
        id = post.getId();
        content = post.getContent();

        commentCount = post.getComments().size();
        writer = post.getWriter().getNickname();
        views = post.getViews();
        writeDateTime = post.getWriteDateTime();
    }
}
