package com.nhnacademy.exam.gateway.service.login;

import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.service.member.MemberService;
import com.nhnacademy.exam.gateway.vo.security.UserDetailsVo;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findMemberById(username);

        if (Objects.isNull(member)) {
            throw new UsernameNotFoundException("그런 유저는 존재하지 않습니다.");
        }

        return new UserDetailsVo(member);
    }
}
