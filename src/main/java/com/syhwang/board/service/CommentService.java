package com.syhwang.board.service;

import com.syhwang.board.entity.Comment;
import com.syhwang.board.entity.Member;
import com.syhwang.board.entity.Post;
import com.syhwang.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void addComment(String content, Post post, Member writer) {
        Comment comment = Comment.builder()
                .content(content)
                .writer(writer)
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Member loginMember) {
        Comment comment = commentRepository.findOne(commentId).orElseThrow(() -> new IllegalStateException("존재하지 않는 댓글입니다"));
        if (!comment.getWriter().getId().equals(loginMember.getId())) {
            throw new IllegalStateException("자신이 쓴 댓글만 삭제할 수 있습니다");
        } else {
            commentRepository.delete(comment);
        }
    }

    public List<Comment> getComments(Post post) {
        return commentRepository.findByPost(post);
    }
}
