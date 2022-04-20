package com.syhwang.board.service;

import com.syhwang.board.entity.Member;
import com.syhwang.board.entity.Post;
import com.syhwang.board.dto.PostRequestDto;
import com.syhwang.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

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
        postRepository.updateView(postId);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPagingPosts(int page) {
        return postRepository.findPage(page);
    }

    public int getTotalPage() {
        return postRepository.findAll().size()/10;
    }

}
