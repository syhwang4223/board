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

    private final String writer;
    private final int views;
    private final LocalDateTime writeDateTime;

    public PostDetailsDto(Post post) {
        if (post.getComments().size() != 0) {
            title = post.getTitle() + " (" + post.getComments().size() + ")";
        } else {
            title = post.getTitle();
        }

        id = post.getId();
        content = post.getContent();
        writer = post.getWriter().getNickname();
        views = post.getViews();
        writeDateTime = post.getWriteDateTime();
    }
}
