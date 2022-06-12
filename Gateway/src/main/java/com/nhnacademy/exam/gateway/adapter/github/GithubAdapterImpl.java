package com.nhnacademy.exam.gateway.adapter.github;

import com.nhnacademy.exam.gateway.domain.git.AccessTokenBody;
import com.nhnacademy.exam.gateway.domain.git.GitUserData;
import java.util.Objects;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GithubAdapterImpl
    implements GithubAdapter {
    private static final String CLIENT_ID = "2da1de0e803cd107c45b";
    private static final String CLIENT_SECRET = "60669d569c884b722c868db3ab2312e05d816a1c";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity request = new HttpEntity(headers);
    private String state;

    public GithubAdapterImpl() {
        headers.add("accept", "application/json");
    }

    @Override
    public String getUriForLoginPageRequest() {
        generateRandomState();

        UriComponents uriComponents =  UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("github.com")
            .path("/login/oauth/authorize")
            .queryParam("client_id", CLIENT_ID)
            .queryParam("state", this.state)
            .queryParam("redirect_uri", "http://localhost:8080/github/login/callback")
            .build();

        return uriComponents.toUriString();
    }

    @Override
    public AccessTokenBody getAccessToken(String code, String state) {
        if (!Objects.equals(state, this.state)) throw new RuntimeException("잘못된 요청입니다. state 불일치");

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("github.com")
            .path("/login/oauth/access_token")
            .queryParam("client_id", CLIENT_ID)
            .queryParam("client_secret", CLIENT_SECRET)
            .queryParam("code", code)
            .build();

        ResponseEntity<AccessTokenBody> responseEntityOfAccessToken =
            restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, request, AccessTokenBody.class);

        return responseEntityOfAccessToken.getBody();
    }

    @Override
    public GitUserData getGitUserData(String accessToken) {
        headers.add("Authorization", "token " + accessToken);

        UriComponents uriComponents =  UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("api.github.com")
            .path("/user")
            .build();

        HttpEntity<GitUserData> responseForUserData =
            restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, request, GitUserData.class);
        headers.clear();


        return responseForUserData.getBody();
    }

    private void generateRandomState() {
        this.state = "i am random";
    }
}
