package com.syhwang.board.controller;

import com.syhwang.board.domain.Comment;
import com.syhwang.board.domain.Member;
import com.syhwang.board.domain.Post;
import com.syhwang.board.dto.PostFormDto;
import com.syhwang.board.service.CommentService;
import com.syhwang.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/new")
    public String writeForm() {
        return "posts/writeForm";
    }

    @PostMapping("/new")
    public String write(@Valid PostFormDto form
            , @SessionAttribute(name = "loginMember") Member loginMember, Model model) {
        Post post = postService.write(form, loginMember);


        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable long postId, Model model) {
        postService.updateView(postId);
        Post post = postService.getPost(postId);

//        List<Comment> comments = commentService.getComments(post);

        model.addAttribute("post", post);
//        model.addAttribute("comments", comments);

        return "posts/postDetail";
    }

    @PostMapping("/{postId}")
    public String comment(@PathVariable long postId, @ModelAttribute(name = "newComment") String newComment
                          , @SessionAttribute(name = "loginMember") Member loginMember) {
        log.debug(newComment);
        Post post = postService.getPost(postId);
        commentService.addComment(newComment, post, loginMember);

        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/comments")
    public String getComments(@PathVariable long postId, Model model) {
        List<Comment> comments = commentService.getComments(postService.getPost(postId));
        model.addAttribute("comments", comments);

        return "posts/commentList";
    }
    
    @PostMapping("/{postId}/comments")
    public String addComment(@PathVariable long postId
            , @SessionAttribute(name = "loginMember") Member loginMember
            , @RequestParam("newComment") String content) {

        log.debug("addComment");

        Post post = postService.getPost(postId);
        commentService.addComment(content, post, loginMember);

        return "posts/commentList";
    }


}
