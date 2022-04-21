package com.syhwang.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    private String title;

    @Lob
    private String content;

    private int views;

    private int likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private LocalDateTime writeDateTime;

    //== 연관 관계 메서드 ==//
    public void addComment(Comment comment, Member writer) {
        comments.add(comment);
        comment.setPost(this);
        comment.setWriter(writer);
    }


    //== 비즈니스 로직 ==//
    public void upViewCount() {
        views += 1;
    }

    public void modify(String title, String content) {
        this.title = title;
        this.content = content;
    }


    @Builder
    public Post(String title, String content, Member writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        writeDateTime = LocalDateTime.now();
        views = 0;
        likes =0;
    }

    protected Post() {}
}
