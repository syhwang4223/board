package com.syhwang.board.dto;

import com.syhwang.board.entity.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class MemberDetailDto {

    private String loginId;
    private int postCount;
    private int commentCount;
    private LocalDateTime joinDateTime;

    public MemberDetailDto(Member member) {
        this.loginId = member.getLoginId();
        this.postCount = member.getPostCount();
        this.commentCount = member.getCommentCount();
        this.joinDateTime = member.getJoinDateTime();
    }

}
