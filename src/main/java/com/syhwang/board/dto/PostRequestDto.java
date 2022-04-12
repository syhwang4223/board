package com.syhwang.board.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostRequestDto() {

    }
}
