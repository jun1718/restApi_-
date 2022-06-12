package com.nhnacademy.exam.gateway.controller.login;

import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final RedisTemplate<String, MemberVo> redisTemplate;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) return "showLoginForm";

        MemberVo memberVo = (MemberVo) redisTemplate.opsForHash().get(session.getId(), "member");
        if (Objects.isNull(memberVo)) return "showLoginForm";
        model.addAttribute("member", memberVo);
        return "showLoginSuccess";
    }
}
