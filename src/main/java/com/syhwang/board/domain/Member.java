package com.syhwang.board.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;


    @Builder
    public Member(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    protected Member() {

    }
}
