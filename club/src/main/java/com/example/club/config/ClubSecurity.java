package com.example.club.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

import com.example.club.handler.ClubLoginSuccessHandler;

@EnableMethodSecurity // 접근 제한을 어노테이션으로 처리
@EnableWebSecurity
@Configuration
public class ClubSecurity {

    @Bean
    ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler();
    };

    // 시큐리티를 적용할 url 상세 설정 => Filter 등록
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, RememberMeServices rememberMeServices) throws Exception {
        http
                // .authorizeHttpRequests(authorize -> authorize
                // .requestMatchers("/", "/users/auth").permitAll()
                // .requestMatchers("/users/member").hasRole("USER")
                // .requestMatchers("/users/admin").hasAnyRole("USER", "MANAGER", "ADMIN"))
                // .formLogin(login -> login.loginPage("/users/login").permitAll()
                // .successHandler(successHandler()))
                // static 아래 경로 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/img/*", "/js/*", "/css/*").permitAll()
                        .anyRequest().permitAll())
                .formLogin(login -> login.successHandler(successHandler()).loginPage("/users/login").permitAll())
                .oauth2Login(login -> login.successHandler(successHandler()))
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                        .logoutSuccessUrl("/"))
                .rememberMe(remember -> remember.rememberMeServices(rememberMeServices));

        return http.build();
    }

    // remember
    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        RememberMeTokenAlgorithm rMeTokenAlgorithm = RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("myKey",
                userDetailsService, rMeTokenAlgorithm);
        rememberMeServices.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
        rememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 7);
        return rememberMeServices;
    }

    // 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
