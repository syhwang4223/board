package com.syhwang.board.repository;

import com.syhwang.board.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    public Optional<Post> findOne(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.of(post);
    }

    public void updateView(Long id) {
        em.createQuery("update Post p set p.views = p.views + 1 where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
    public void updateLikes(Long id) {
        em.createQuery("update Post p set p.likes = p.likes + 1 where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }



    public void delete(Post post) {
        em.remove(post);
    }

}
