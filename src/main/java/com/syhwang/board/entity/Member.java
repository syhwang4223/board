package com.syhwang.board.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;


    @Builder
    public Member(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    protected Member() {

    }
}
