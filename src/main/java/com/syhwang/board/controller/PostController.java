package com.syhwang.board.controller;

import com.syhwang.board.Login;
import com.syhwang.board.dto.CommentDetailsDto;
import com.syhwang.board.dto.PostDetailsDto;
import com.syhwang.board.entity.Member;
import com.syhwang.board.entity.Post;
import com.syhwang.board.dto.PostRequestDto;
import com.syhwang.board.service.CommentService;
import com.syhwang.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;


    // 게시글 생성
    @GetMapping("/new")
    public String writeForm(Model model) {
        model.addAttribute("post", new PostRequestDto());
        return "posts/writeForm";
    }

    @PostMapping("/new")
    public String write(@Validated @ModelAttribute("post") PostRequestDto form, BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , @Login Member loginMember, Model model) {

        log.debug("title = {}, content = {}", form.getTitle(), form.getContent());

        if (bindingResult.hasErrors()) {
            log.debug("errors= {}", bindingResult);
            return "posts/writeForm";
        }

        Post post = postService.write(form.getTitle(), form.getContent(), loginMember);
        redirectAttributes.addAttribute("postId", post.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/posts/{postId}/detail";
    }

    // 게시글 조회
    @GetMapping("/{postId}/detail")
    public String post(@PathVariable long postId, Model model) {
        postService.updateViewCnt(postId);
        PostDetailsDto postDetailsDto = new PostDetailsDto(postService.getPost(postId));

        model.addAttribute("post", postDetailsDto);

        return "posts/postDetail";
    }


    // 게시글 수정
    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable long postId, Model model) {
        Post findPost = postService.getPost(postId);
        PostDetailsDto post = new PostDetailsDto(findPost);
        model.addAttribute("post", post);

        return "posts/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable long postId, @ModelAttribute("post") PostRequestDto form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("errors= {}", bindingResult);
            return "posts/writeForm";
        }

        postService.modify(postId, form.getTitle(), form.getContent());
        return "redirect:/posts/{postId}/detail";
    }


    // 게시글 삭제
    @PostMapping("/{postId}/delete")
    public String delete(@Login Member loginMember, @PathVariable long postId) {
        postService.delete(postId, loginMember);
        return "redirect:/";
    }


    // 좋아요
    @PostMapping("/{postId}/like")
    public String recommend(@Login Member loginMember, @PathVariable long postId, Model model) {

        if (loginMember == null) {
            /**
             * 로그인이 필요하다는 팝업
             */
        }
        
        postService.updateLikes(postId);

        return "redirect:/posts/{postId}/detail";
    }




    //== 댓글 ==//

    @GetMapping("/{postId}/comments")
    public String getComments(@PathVariable long postId, Model model) {
        List<CommentDetailsDto> comments = commentService.getComments(postService.getPost(postId))
                .stream()
                .map(CommentDetailsDto::new)
                .collect(Collectors.toList());

        model.addAttribute("comments", comments);

        return "posts/commentList";
    }
    
    @PostMapping("/{postId}/comments/new")
    public String addComment(@PathVariable long postId
            , @Login Member loginMember
            , @RequestParam("newComment") String content) {

        if (!content.isBlank()) {
            Post post = postService.getPost(postId);
            commentService.addComment(content, post, loginMember);
        }

        return "posts/commentList";
    }

    @PostMapping("/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable long postId, @PathVariable long commentId
            , @Login Member loginMember) {

        commentService.deleteComment(commentId, loginMember);

        return "posts/commentList";
    }


}
