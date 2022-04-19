package com.syhwang.board.dto;

import com.syhwang.board.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberDetailDto {

    private String loginId;
    private String emailAddress;
    private String nickname;
    private int postCount;
    private int commentCount;
    private LocalDateTime joinDateTime;

    public MemberDetailDto(Member member) {
        this.loginId = member.getLoginId();
        this.emailAddress = member.getEmailAddress();
        this.nickname = member.getNickname();
        this.postCount = member.getPostCount();
        this.commentCount = member.getCommentCount();
        this.joinDateTime = member.getJoinDateTime();
    }

}
