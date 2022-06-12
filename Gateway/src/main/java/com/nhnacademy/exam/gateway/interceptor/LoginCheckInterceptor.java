package com.nhnacademy.exam.gateway.interceptor;

import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor {
    private final List<String> urlList = new ArrayList();
    private final RedisTemplate<String, MemberVo> redisTemplate;

    public LoginCheckInterceptor(
        RedisTemplate<String, MemberVo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void whiteList() {
        urlList.add("/login");
        urlList.add("/member/create");
        urlList.add("/member/createProcessing");
        urlList.add("/github/login");
        urlList.add("/github/login/callback");
//        urlList.add("/residents");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        whiteList();
        if (!urlList.contains(request.getRequestURI())) {
            HttpSession session = request.getSession(false);
            if (Objects.isNull(session)) {
                response.sendRedirect("/login");
                return false;
            }
            MemberVo memberVo =
                (MemberVo) redisTemplate.opsForHash().get(session.getId(), "member");
            if (Objects.isNull(memberVo)) {
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        urlList.clear();
    }
}
