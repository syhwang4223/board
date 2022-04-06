package com.syhwang.board.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
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
    @NotEmpty
    private String content;

    private int likes;

    @Builder
    public Comment(String content, Member writer, Post post) {
        this.content = content;
        this.writer = writer;
        this.post = post;
        likes = 0;
    }

    protected Comment(){}


}
