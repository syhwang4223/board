package com.syhwang.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Lob
    private String content;

    private int likes;

    @Builder
    public Comment(Member writer, Post post, String content) {
        this.writer = writer;
        this.post = post;
        this.content = content;
    }

}
