package com.nhnacademy.exam.gateway.service.login;


import com.nhnacademy.exam.gateway.domain.git.AccessTokenBody;
import com.nhnacademy.exam.gateway.domain.git.GitUserData;
import javax.servlet.http.HttpSession;

public interface GithubLoginService {
    AccessTokenBody getAccessToken(String code, String state);

    GitUserData getGitUserData(String accessToken);

    String getUriForLoginPageRequest();

    Boolean checkEmail(String email, HttpSession session);
}
