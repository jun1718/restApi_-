package com.nhnacademy.exam.gateway.vo.security;

import com.nhnacademy.exam.gateway.domain.member.Member;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@EqualsAndHashCode
public class UserDetailsVo implements UserDetails {
    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.member.getAuthority()));
    }

    @Override
    public String getPassword() {
        return this.member.getPw();
    }

    @Override
    public String getUsername() {
        return this.member.getId();
    }

    public Member getMember() {
        return member;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
