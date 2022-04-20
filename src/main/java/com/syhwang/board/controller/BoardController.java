package com.syhwang.board.controller;

import com.syhwang.board.dto.PostDetailsDto;
import com.syhwang.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;

    @GetMapping("/")
    public String home() {
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String boardMain(@RequestParam(defaultValue = "1", name = "page") int page, Model model) {

        List<PostDetailsDto> postDtos = postService.getPagingPosts(page).stream()
                .map(PostDetailsDto::new)
                .collect(Collectors.toList());
        int lastPage = postService.getTotalPage();

        model.addAttribute("posts", postDtos);
        model.addAttribute("lastPage", lastPage);

        return "posts/board";
    }

}
