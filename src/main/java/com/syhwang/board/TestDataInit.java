package com.syhwang.board;

import com.syhwang.board.entity.Member;
import com.syhwang.board.entity.Post;
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

        for (int i = 0; i < 100; i++) {
            Member member = new Member("test" + i, "password", "테스트" + i, "test" + i + "@test.com");
            memberService.signup(member);

            Post post = postService.write("게시글 제목" + i, "게시글 내용" + i, member);
            commentService.addComment("댓글 테스트", post, member);
        }


    }
}
