package com.syhwang.board.controller;

import com.syhwang.board.domain.Member;
import com.syhwang.board.dto.LoginFormDto;
import com.syhwang.board.dto.SignUpFormDto;
import com.syhwang.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginFormDto loginFormDto, HttpServletRequest request) {
        log.debug("login: loginId = {}, password = {}", loginFormDto.getLoginId(), loginFormDto.getPassword());

        Member loginMember = memberService.login(loginFormDto.getLoginId(), loginFormDto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignUpFormDto form) {
        log.debug("loginId = {}, password = {}", form.getLoginId(), form.getPassword());

        Member member = memberService.signup(form);
        log.debug("new member registered: id = {}, loginId = {}"
                , member.getId(), member.getLoginId());
        return "redirect:/";
    }


}
