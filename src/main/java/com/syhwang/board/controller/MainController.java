package com.syhwang.board.controller;

import com.syhwang.board.dto.PostDetailsDto;
import com.syhwang.board.entity.Post;
import com.syhwang.board.service.CommentService;
import com.syhwang.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping("/")
    public String boardMain(Model model) {

        List<PostDetailsDto> postDtos = postService.getAllPosts().stream()
                .map(PostDetailsDto::new)
                .collect(Collectors.toList());

        model.addAttribute("posts", postDtos);

        return "posts/board";
    }

}
