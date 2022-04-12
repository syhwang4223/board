package com.syhwang.board.controller;

import com.syhwang.board.entity.Member;
import com.syhwang.board.dto.LoginDto;
import com.syhwang.board.dto.SignUpFormDto;
import com.syhwang.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new LoginDto());
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("member") LoginDto loginDto, BindingResult bindingResult, Model model, HttpServletRequest request) {

        log.debug("loginId={}, password={}", loginDto.getLoginId(), loginDto.getPassword());

        if (bindingResult.hasErrors()) {
            log.debug("errors= {}", bindingResult);
            return "loginForm";
        }

        Member loginMember = memberService.login(loginDto.getLoginId(), loginDto.getPassword());
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
