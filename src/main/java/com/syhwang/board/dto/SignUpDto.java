package com.syhwang.board.dto;

import com.syhwang.board.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;

    @NotBlank
    private String nickname;
    @Email
    @NotBlank
    private String emailAddress;

    public SignUpDto(String loginId, String password, String passwordConfirm) {
        this.loginId = loginId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public SignUpDto() {
    }

    public Member toMember() {
        return new Member(loginId, password, nickname, emailAddress);
    }
}
