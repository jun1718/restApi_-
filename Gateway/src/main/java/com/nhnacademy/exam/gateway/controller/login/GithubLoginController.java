package com.nhnacademy.exam.gateway.controller.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.git.AccessTokenBody;
import com.nhnacademy.exam.gateway.domain.git.GitUserData;
import com.nhnacademy.exam.gateway.exception.NoEmailException;
import com.nhnacademy.exam.gateway.service.login.GithubLoginService;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GithubLoginController {
    private final GithubLoginService githubLoginService;

    @GetMapping("/github/login")
    public String goLoginPageRequest(HttpServletResponse response) throws IOException {
        String uri = githubLoginService.getUriForLoginPageRequest();
        return "redirect:" + uri;
    }

    @GetMapping("/github/login/callback")
    public String checkLoginThroughEmail(@RequestParam("code") String code,
                          @RequestParam("state") String state,
                                         HttpSession session) {

        AccessTokenBody accessTokenBody = githubLoginService.getAccessToken(code, state);

        GitUserData gitUserData = githubLoginService.getGitUserData(accessTokenBody.getAccessToken());
        Boolean isEmailExist = githubLoginService.checkEmail(gitUserData.getEmail(), session);

        if (!isEmailExist) {
            throw new NoEmailException("github에 로그인된 사용자 " + gitUserData.getId() +
                "님의 email과 부합하는 사용자가 우리 어플리케이션에선 존재하지 않습니다. 회원가입을 먼저 하세요.");
        }

        return "redirect:/login";
    }
}