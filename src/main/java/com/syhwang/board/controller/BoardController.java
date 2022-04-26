package com.syhwang.board.controller;

import com.syhwang.board.dto.PostDetailsDto;
import com.syhwang.board.entity.Post;
import com.syhwang.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;

    @GetMapping("/")
    public String home() {
        return "redirect:/board";
    }

//    @GetMapping("/board")
//    public String boardMain(@RequestParam(defaultValue = "1", name = "page") int page, Model model) {
//
//        List<PostDetailsDto> postDtos = postService.getPagingPosts(page).stream()
//                .map(PostDetailsDto::new)
//                .collect(Collectors.toList());
//        int lastPage = postService.getTotalPage();
//
//        model.addAttribute("posts", postDtos);
//        model.addAttribute("lastPage", lastPage);
//
//        return "posts/board";
//    }

    @GetMapping("/board")
    public String index(Pageable pageable, Model model) {

        Page<Post> page = postService.getPostList(pageable);
        Page<PostDetailsDto> posts = page.map(PostDetailsDto::new);

        model.addAttribute("posts", posts);
        return "posts/board";
    }

    
    // 제목으로 검색
    @GetMapping("/board/search")
    public String search(@ModelAttribute("keyword") String keyword, Pageable pageable, Model model) {

        log.debug("search keyword = {}", keyword);

        Page<Post> searchList = postService.search(keyword, pageable);
        Page<PostDetailsDto> posts = searchList.map(PostDetailsDto::new);

        model.addAttribute("posts", posts);
        return "posts/searchPage";

    }
}
