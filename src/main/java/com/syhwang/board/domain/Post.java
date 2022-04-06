package com.syhwang.board.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    @NotEmpty
    private String title;

    @NotEmpty
    @Lob
    private String content;

    private int views;

    private int likes;

    private LocalDateTime writeDateTime;

    @Builder
    public Post(String title, String content, Member writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        writeDateTime = LocalDateTime.now();
        views = 0;
        likes =0;
    }

    public void upViewCount() {
        views += 1;
    }

    protected Post() {}
}
