package com.syhwang.board.controller;

import com.syhwang.board.domain.Post;
import com.syhwang.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping("/")
    public String boardMain(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);

        return "posts/board";
    }

}
