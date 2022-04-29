package com.syhwang.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;
    private String nickname;
    private String emailAddress;

    private LocalDateTime joinDateTime;
    private Integer postCount; // 작성 글 수
    private Integer commentCount; // 작성 댓글 수

    public Member(String loginId, String password, String nickname, String emailAddress) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.emailAddress = emailAddress;
        joinDateTime = LocalDateTime.now();
        postCount = 0;
        commentCount = 0;
    }

    public void update(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
