package com.syhwang.board.service;

import com.syhwang.board.entity.Like;
import com.syhwang.board.entity.Member;
import com.syhwang.board.entity.Post;
import com.syhwang.board.repository.LikeRepository;
import com.syhwang.board.repository.PostJpaRepository;
import com.syhwang.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostJpaRepository postJpaRepository;
    private final LikeRepository likeRepository;

    // 게시글 작성
    @Transactional
    public Post write(String title, String content, Member writer) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
        return postRepository.save(post);
    }

    public Post getPost(Long postId) {
        return postRepository
                .findOne(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
    }

    // 게시글 조회수 증가
    // 게시글 열람
    @Transactional
    public void updateViewCnt(Long postId) {
        Post findPost = getPost(postId);
        findPost.upViewCount();
    }


    // 게시글 수정
    @Transactional
    public void modify(Long postId, String title, String content) {
        Post findPost = getPost(postId);
        findPost.modify(title, content);
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long postId, Member writer) {
        Post findPost = getPost(postId);
        if (findPost.getWriter().getId().equals(writer.getId())) {
            postRepository.delete(findPost);
        } else {
            throw new IllegalStateException("본인이 작성한 글만 지울 수 있습니다.");
        }
    }

    // 게시글 추천
    @Transactional
    public String updateLikes(Long postId, Member member) {
        Post findPost = getPost(postId);
        Optional<Like> like = likeRepository.findByPostAndMember(findPost, member);

        if (like.isEmpty()) {
            findPost.upLikesCount();
            likeRepository.save(new Like(member, findPost));
            return "추천했습니다";
        } else {
            findPost.downLikesCount();
            likeRepository.delete(like.get());
            return "추천을 취소했습니다.";
        }
    }

    // 페이징
    public Page<Post> getPostList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by("id").descending()); // <- Sort 추가

        return postJpaRepository.findAll(pageable);
    }

    // 검색
    public Page<Post> search(String keyword, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by("id").descending()); // <- Sort 추가

        return postJpaRepository.findByTitleContaining(keyword, pageable);
    }

}
