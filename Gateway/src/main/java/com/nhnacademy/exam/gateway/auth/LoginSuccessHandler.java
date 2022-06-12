package com.nhnacademy.exam.gateway.auth;

import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.service.member.MemberService;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import com.nhnacademy.exam.gateway.vo.security.UserDetailsVo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final RedisTemplate<String, MemberVo> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
        throws IOException, ServletException {
        HttpSession session = request.getSession();
        response.sendRedirect("/login");

        if (Objects.isNull(session)) return;
        UserDetailsVo userDetailsVo = (UserDetailsVo)authentication.getPrincipal();
        Member member = userDetailsVo.getMember();
        MemberVo memberVo = new MemberVo(member.getMemberNo(), member.getId(), member.getEmail(), member.getMemberStatus(), member.getAuthority());

        redisTemplate.opsForHash().put(session.getId(), "member", memberVo);
    }
}
