package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // 시큐리티를 적용할 url 상세 설정 => Filter 등록
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // static 하위 폴더 경로 필터 무조건 통과 => 컨트롤러에서 접근 제한 설정
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/img/*", "/js/*", "/css/*", "/assets/*").permitAll()
                .anyRequest().permitAll());
        http.formLogin(login -> login.loginPage("/member/login").permitAll());
        return http.build();
    }

    // remember
    // @Bean
    // RememberMeServices rememberMeServices(UserDetailsService userDetailsService)
    // {
    // RememberMeTokenAlgorithm rMeTokenAlgorithm = RememberMeTokenAlgorithm.SHA256;
    // TokenBasedRememberMeServices rememberMeServices = new
    // TokenBasedRememberMeServices("myKey",
    // userDetailsService, rMeTokenAlgorithm);
    // rememberMeServices.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
    // rememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 7);
    // return rememberMeServices;
    // }

    // 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
