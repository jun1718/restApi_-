package com.nhnacademy.exam.accountapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.exam.accountapi.dto.MemberDto;
import com.nhnacademy.exam.accountapi.dto.MemberDtoCreateComposition;
import com.nhnacademy.exam.accountapi.dto.MemberDtoCreateCompositionClass;
import com.nhnacademy.exam.accountapi.entity.Member;
import com.nhnacademy.exam.accountapi.repository.MemberRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@Rollback
class MemberSelectController_RandomPortTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private Member member;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        member = new Member("nhn2", "1234", "2nhn.academy.com", "가입", "ROLE_USER");
    }

    @Test
    void findMemberDtoByMemberNo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Member> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Member>
            response = restTemplate.exchange("/members/1", HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<Member>(){});

        Member body = response.getBody();
        assertThat(body.getMemberNo())
            .isEqualTo(1);

        assertThat(body.getMemberStatus())
            .isEqualTo("가입");
    }

    @Test
    void findMemberDtoById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Member> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Member>
            response = restTemplate.exchange("/members?id=nhn1", HttpMethod.GET, httpEntity,
            Member.class);

        Member body = response.getBody();

        assertThat(body.getMemberStatus())
            .isEqualTo("가입");
        assertThat(body.getId())
            .isEqualTo("nhn1");
        assertThat(body.getEmail())
            .isEqualTo("1nhn.academy.com");
    }

    @Test
    void findAllCreateComposition() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Member> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<List<MemberDtoCreateCompositionClass>>
            response = restTemplate.exchange("/members/find/createComposition", HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<List<MemberDtoCreateCompositionClass>>() {});

        List<MemberDtoCreateCompositionClass> body = response.getBody();

        assertThat(body.get(0).getId())
            .isEqualTo("1");
        assertThat(body.get(0).getMemberNo())
            .isEqualTo(3L);
    }

    @Test
    void findMemberDtoByEmail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Member> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Member>
            response = restTemplate.exchange("/members/email?email=" + "admin@nhn.com", HttpMethod.GET, httpEntity, Member.class);

        Member body = response.getBody();

        assertThat(body.getId())
            .isEqualTo("admin");
        assertThat(body.getMemberNo())
            .isEqualTo(7L);
    }
}