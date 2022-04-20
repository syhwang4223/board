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

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public void updateView(Long id) {
        em.createQuery("update Post p set p.views = p.views + 1 where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    /**
     * 최근에 작성된 게시글 순으로 페이징 기능 추가하기
     */

    public List<Post> findPage(int page) {
        return em.createQuery("select p from Post p" +
                " order by p.id desc", Post.class)
                .setFirstResult(10 * (page-1))
                .setMaxResults(10)
                .getResultList();
    }

}
