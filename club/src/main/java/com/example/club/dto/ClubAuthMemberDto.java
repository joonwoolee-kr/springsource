package com.example.club.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// UserDetails <- User <- ClubAuthMemberDto

@ToString
@Setter
@Getter
public class ClubAuthMemberDto extends User implements OAuth2User {

    private String email;

    private String name;

    private boolean fromSocial;

    private String password;

    private Map<String, Object> attr; // 소셜 로그인에서 넘어오는 값 담기

    // security에서 username == id
    public ClubAuthMemberDto(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
        this.password = password;
    }

    public ClubAuthMemberDto(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
