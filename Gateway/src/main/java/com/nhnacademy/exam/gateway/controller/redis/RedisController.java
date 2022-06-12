package com.nhnacademy.exam.gateway.controller.redis;

import com.nhnacademy.exam.gateway.domain.member.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RedisController {
    private final RedisTemplate<String, Member> redisTemplate;

    @GetMapping("/redis")
    public String redis(Model model, HttpSession session) {

//        Member member = new Member(1L, "idid", "pwpw", "email", "authority");
//        redisTemplate.opsForHash().put(session.getId(), "username", "방가");
//        redisTemplate.opsForHash().put(session.getId(), "authority", "하이");
//        redisTemplate.opsForHash().put(session.getId(), "member", member);


//        Object a = redisTemplate.opsForHash().get(session.getId(), "member");
//        Member a1 = (Member) redisTemplate.opsForHash().get(session.getId(), "member");


//        model.addAttribute("redis", a);
        return "showRedis";
    }
}
