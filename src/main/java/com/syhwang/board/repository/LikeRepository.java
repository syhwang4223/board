package com.syhwang.board.repository;

import com.syhwang.board.entity.Like;
import com.syhwang.board.entity.Member;
import com.syhwang.board.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    public Optional<Like> findByPostAndMember(Post post, Member member) {
        return em.createQuery("select l from Like l where l.member = :member and l.post = :post", Like.class)
                .setParameter("member", member)
                .setParameter("post", post)
                .getResultList()
                .stream()
                .findAny();
    }

    public void save(Like like) {
        em.persist(like);
    }

    public void delete(Like like) {
        em.remove(like);
    }
}
