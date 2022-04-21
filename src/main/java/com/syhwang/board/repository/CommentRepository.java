package com.syhwang.board.repository;

import com.syhwang.board.entity.Comment;
import com.syhwang.board.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    public Optional<Comment> findOne(Long commentId) {
        return Optional.of(em.find(Comment.class, commentId));
    }

    public List<Comment> findByPost(Post post) {
        return em.createQuery("select c from Comment c join c.post p where p.id = :postId", Comment.class)
                .setParameter("postId", post.getId())
                .getResultList();
    }

    public void delete(Comment comment) {
        em.remove(comment);
    }

}
