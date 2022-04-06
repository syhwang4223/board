package com.syhwang.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginFormDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public LoginFormDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

}
