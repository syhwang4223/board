package com.syhwang.board.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberUpdateDto {

    @NotBlank
    private String nickname;

    @NotBlank
    private String currentPassword;
    @NotBlank
    private String newPassword;

}
