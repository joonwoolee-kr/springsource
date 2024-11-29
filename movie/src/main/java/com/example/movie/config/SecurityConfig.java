package com.example.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/assets/**", "/css/**", "/js/**", "/upload/**").permitAll()
                .requestMatchers("/movie/list").permitAll()
                .anyRequest().authenticated());
        http.formLogin(login -> login.loginPage("/member/login").permitAll());

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        // http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
