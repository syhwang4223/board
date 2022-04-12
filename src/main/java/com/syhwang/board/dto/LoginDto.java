package com.syhwang.board.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class LoginDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

}
