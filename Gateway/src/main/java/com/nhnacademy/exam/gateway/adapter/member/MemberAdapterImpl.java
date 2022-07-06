package com.nhnacademy.exam.gateway.adapter.member;

import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.vo.member.MemberVoCreateComposition;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class MemberAdapterImpl
    implements MemberAdapter {
    private final RestTemplate restTemplate;

    @Override
    public Member findMemberByMemberNo(Long memberNo) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9090")
            .path("/members/" + memberNo)
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Member> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, requestEntity, Member.class);

        return responseEntity.getBody();
    }

    @Override
    public Member findMemberById(String username) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9090")
            .path("/members")
            .queryParam("id", username)
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Member> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, requestEntity, Member.class);

        return responseEntity.getBody();
    }

    @Override
    public String createMember(Member member) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9090")
            .path("/member/create")
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity(member, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

    @Override
    public Member findByEmail(String email) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9090")
            .path("/members/email")
            .queryParam("email", email)
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Member> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, requestEntity, Member.class);

        return responseEntity.getBody();
    }

    @Override
    public List<MemberVoCreateComposition> findAllCreateComposition() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9090")
            .path("/members/find/createComposition")
//            GET http://localhost:8080/members/*/InformationOfCreatingForUser TODO 5 : 설계를 select low를 줄일때 이렇게하면되는지?
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<List<MemberVoCreateComposition>> responseEntity = restTemplate.exchange(uriComponents.toUri(),
            HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<MemberVoCreateComposition>>() {});

        return responseEntity.getBody();
    }
}
