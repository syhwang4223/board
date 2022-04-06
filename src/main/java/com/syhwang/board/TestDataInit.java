package com.syhwang.board;

import com.syhwang.board.domain.Member;
import com.syhwang.board.domain.Post;
import com.syhwang.board.dto.PostFormDto;
import com.syhwang.board.dto.SignUpFormDto;
import com.syhwang.board.service.CommentService;
import com.syhwang.board.service.MemberService;
import com.syhwang.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;
    /**
     * 더미 데이터 추가
     */
    @PostConstruct
    public void init() {
        Member member1 = memberService.signup(new SignUpFormDto("member1", "member1", "member1"));
        Member member2 = memberService.signup(new SignUpFormDto("member2", "member2", "member2"));

        Post post1 = postService.write(new PostFormDto("오늘의 일기1", "나는 오늘 아침밥을 먹었다."), member1);
        Post post2 = postService.write(new PostFormDto("오늘의 일기2", "나는 오늘 점심밥을 먹었다."), member1);
        Post post3 = postService.write(new PostFormDto("오늘의 일기3", "나는 오늘 저녁밥을 먹었다."), member1);

        commentService.addComment("뭔 하루가 먹다가 다가네;", post3, member2);
    }
}
