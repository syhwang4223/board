package com.syhwang.board;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                // 홈 화면, 회원 가입, 로그인, 로그아웃 등에 대한 요청은 제외
                // 비회원도 게시글, 댓글 읽기는 가능
                .excludePathPatterns("/", "/signup", "/login", "/logout",
                        "/posts/*/detail", "/posts/*/comments",
                        "/css/**", "/*.ico", "/error");

    }
}
