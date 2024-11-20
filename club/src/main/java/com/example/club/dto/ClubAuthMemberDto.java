package com.example.club.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// UserDetails <- User <- ClubAuthMemberDto

@ToString
@Setter
@Getter
public class ClubAuthMemberDto extends User {

    private String email;

    private String name;

    private boolean fromSocial;

    // security에서 username == id
    public ClubAuthMemberDto(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }
}
