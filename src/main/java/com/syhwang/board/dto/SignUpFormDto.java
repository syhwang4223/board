package com.syhwang.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
public class SignUpFormDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;

    public SignUpFormDto(String loginId, String password, String passwordConfirm) {
        this.loginId = loginId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
