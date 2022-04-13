package com.syhwang.board.controller;

import com.syhwang.board.dto.PostDetailsDto;
import com.syhwang.board.entity.Comment;
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

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/new")
    public String writeForm(Model model) {
        model.addAttribute("post", new PostRequestDto());
        return "posts/writeForm";
    }

    @PostMapping("/new")
    public String write(@Validated @ModelAttribute("post") PostRequestDto form, BindingResult bindingResult, RedirectAttributes redirectAttributes
            , @SessionAttribute(name = "loginMember") Member loginMember, Model model) {

        log.debug("title = {}, content = {}", form.getTitle(), form.getContent());

        if (bindingResult.hasErrors()) {
            log.debug("errors= {}", bindingResult);
            return "posts/writeForm";
        }

        Post post = postService.write(form, loginMember);
        redirectAttributes.addAttribute("postId", post.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable long postId, Model model) {
        postService.updateViewCnt(postId);
        PostDetailsDto postDetailsDto = new PostDetailsDto(postService.getDetails(postId));

        model.addAttribute("post", postDetailsDto);

        return "posts/postDetail";
    }


    @GetMapping("/{postId}/comments")
    public String getComments(@PathVariable long postId, Model model) {
        List<Comment> comments = commentService.getComments(postService.getDetails(postId));
        model.addAttribute("comments", comments);

        return "posts/commentList";
    }
    
    @PostMapping("/{postId}/comments")
    public String addComment(@PathVariable long postId
            , @SessionAttribute(name = "loginMember") Member loginMember
            , @RequestParam("newComment") String content) {

        log.debug("addComment");

        Post post = postService.getDetails(postId);
        commentService.addComment(content, post, loginMember);

        return "posts/commentList";
    }


}
