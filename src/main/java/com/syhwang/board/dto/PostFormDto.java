package com.syhwang.board.dto;

import com.syhwang.board.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostFormDto {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public PostFormDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
