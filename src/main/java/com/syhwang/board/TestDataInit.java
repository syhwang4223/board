package com.syhwang.board;

import com.syhwang.board.entity.Member;
import com.syhwang.board.entity.Post;
import com.syhwang.board.dto.PostRequestDto;
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
        Member member1 = memberService.signup(new Member("member1", "member1", "회원1", "test1@test.com"));
        Member member2 = memberService.signup(new Member("member2", "member2", "회원2", "test2@test.com"));
        Member member3 = memberService.signup(new Member("member3", "member3", "회원3", "test3@test.com"));

        Post post1 = postService.write(new PostRequestDto("오늘의 일기1", "나는 오늘 아침밥을 먹었다."), member1);
        Post post2 = postService.write(new PostRequestDto("오늘의 일기2", "나는 오늘 점심밥을 먹었다."), member1);
        Post post3 = postService.write(new PostRequestDto("오늘의 일기3", "나는 오늘 저녁밥을 먹었다."), member1);

        commentService.addComment("뭔 하루가 먹다가 다가네;", post3, member2);
        commentService.addComment("김밥 한줄 놓고갑니다 ^_^ @))))))", post1, member2);
        commentService.addComment("김밥 한줄 놓고갑니다 ^_^ @))))))", post1, member2);
        commentService.addComment("@>-----장미꽃 한송이 두고갑니다^^", post1, member3);
    }
}
