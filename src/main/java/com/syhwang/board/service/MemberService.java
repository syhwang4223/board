package com.syhwang.board.service;

import com.syhwang.board.domain.Member;
import com.syhwang.board.dto.SignUpFormDto;
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

    /**
     * 잘못된 비밀번호, 또는 중복 아이디 등등의 잘못된 요청이 발생했을 때
     * 그걸 사용자에게 알려줘야 하기 때문에 바로 예외를 던지지 않고
     * 컨트롤러로 넘겨줘서 컨트롤러에서 처리하게 하는게 나은건지??
     *
     * 근데 오류처리는 나중에 따로 할거니까 일단은 여기서 이렇게만 해두자!
     *
     * 일단은 주요 로직 먼지 완성하기~
     */

    @Transactional
    public Member signup(SignUpFormDto signUpFormDto) {

        duplicateValidation(signUpFormDto.getLoginId());
        log.debug("password = {}, passwordConfirm = {}", signUpFormDto.getPassword(), signUpFormDto.getPasswordConfirm());

        invalidPasswordValidation(signUpFormDto.getPassword(), signUpFormDto.getPasswordConfirm());
        Member member = Member.builder()
                .loginId(signUpFormDto.getLoginId())
                .password(signUpFormDto.getPassword())
                .build();
        return memberRepository.save(member);
    }

    private void invalidPasswordValidation(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new RuntimeException("비밀번호를 다시 확인해주세요.");
        }
    }

    private void duplicateValidation(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isPresent()) {
            throw new RuntimeException("중복된 아이디입니다.");
        }
    }

    public Member login(String loginId, String password) {
         return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 잘못되었습니다."));

    }


}
