package com.syhwang.board.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

}
