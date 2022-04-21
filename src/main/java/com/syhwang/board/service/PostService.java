package com.syhwang.board.service;

import com.syhwang.board.entity.Member;
import com.syhwang.board.entity.Post;
import com.syhwang.board.dto.PostRequestDto;
import com.syhwang.board.repository.PostPagingRepository;
import com.syhwang.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostPagingRepository postPagingRepository;

    @Transactional
    public Post write(PostRequestDto postRequestDto, Member writer) {
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .writer(writer)
                .build();
        return postRepository.save(post);
    }

    public Post getDetails(Long postId) {
        return postRepository
                .findOne(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
    }

    @Transactional
    public void updateViewCnt(Long postId) {
        Post findPost = postRepository.findOne(postId).orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));
        findPost.upViewCount();
//        postRepository.updateView(postId);
    }


    @Transactional
    public void modify(Long postId, String title, String content) {
        Post findPost = postRepository.findOne(postId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
        findPost.modify(title, content);
    }

    @Transactional
    public void delete(Long postId, Member writer) {
        Post findPost = postRepository.findOne(postId).orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));
        if (findPost.getWriter().getId().equals(writer.getId())) {
            postRepository.delete(findPost);
        } else {
            throw new IllegalStateException("본인이 작성한 글만 지울 수 있습니다.");
        }
    }

    @Transactional
    public void updateLikes(Long postId) {

        Post findPost = postRepository.findOne(postId).orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));
        /**
         *  한 번 누르면 추천, 다시한 번 누르면 추천 취소가 되어야 함
         */
        findPost.upLikesCount();
    }

    public Page<Post> getPostList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by("id").descending()); // <- Sort 추가

        return postPagingRepository.findAll(pageable);
    }

}
