package com.syhwang.board.service;

import com.syhwang.board.entity.Member;
import com.syhwang.board.dto.SignUpDto;
import com.syhwang.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member signup(Member member) {
        return memberRepository.save(member);
    }

    public Boolean isValidPassword(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    public Boolean existLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        return findMember.isPresent();
    }

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);

    }

    public void update(Member member, String nickname, String password) {
        member.update(nickname, password);
    }


}
