package com.syhwang.board.service;

import com.syhwang.board.domain.Member;
import com.syhwang.board.domain.Post;
import com.syhwang.board.dto.PostFormDto;
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
    public Post write(PostFormDto postFormDto, Member writer) {
        Post post = Post.builder()
                .title(postFormDto.getTitle())
                .content(postFormDto.getContent())
                .writer(writer)
                .build();
        return postRepository.save(post);
    }

    public Post getPost(Long postId) {
        return postRepository
                .findOne(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
    }

    @Transactional
    public void updateView(Long postId) {
        postRepository.updateView(postId);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
