package com.syhwang.board.service;

import com.syhwang.board.domain.Comment;
import com.syhwang.board.domain.Member;
import com.syhwang.board.domain.Post;
import com.syhwang.board.dto.CommentDto;
import com.syhwang.board.repository.CommentRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void addComment(String content, Post post, Member writer) {
        Comment comment = Comment.builder()
                .content(content)
                .writer(writer)
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    public List<Comment> getComments(Post post) {
        return commentRepository.findByPost(post);
    }
}
