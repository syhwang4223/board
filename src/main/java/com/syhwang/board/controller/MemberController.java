package com.syhwang.board.controller;

import com.syhwang.board.entity.Member;
import com.syhwang.board.dto.LoginDto;
import com.syhwang.board.dto.SignUpDto;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(@Validated @ModelAttribute("member") LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request) {

        log.debug("loginId={}, password={}", loginDto.getLoginId(), loginDto.getPassword());

        if (bindingResult.hasErrors()) {
            log.debug("errors= {}", bindingResult);
            return "loginForm";
        }

        Member loginMember = memberService.login(loginDto.getLoginId(), loginDto.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호를 다시 확인해주세요.");
            return "loginForm";
        }
        
        // 로그인 성공
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
    public String signupForm(Model model) {
        model.addAttribute("member", new SignUpDto());
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("member") SignUpDto form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.debug("loginId = {}, password = {}", form.getLoginId(), form.getPassword());

        if (memberService.existLoginId(form.getLoginId())) {
            bindingResult.reject("signupFail", "이미 존재하는 아이디입니다.");
        }
        else if (!memberService.isValidPassword(form.getPassword(), form.getPasswordConfirm())) {
            bindingResult.reject("signupFail", "비밀번호를 다시 확인해주세요.");
        }

        if (bindingResult.hasErrors()) {
            log.debug("error = {}", bindingResult);
            return "signupForm";
        }


        Member member = form.toMember();
        memberService.signup(member);

        redirectAttributes.addAttribute("status", true);

        return "redirect:/login";
    }


}
