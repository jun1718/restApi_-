package com.nhnacademy.exam.gateway.adapter;

import com.nhnacademy.exam.gateway.domain.git.AccessTokenBody;
import com.nhnacademy.exam.gateway.domain.git.GitUserData;

public interface GithubAdapter {
    String getUriForLoginPageRequest();
    AccessTokenBody getAccessToken(String code, String state);

    GitUserData getGitUserData(String accessToken);
}
