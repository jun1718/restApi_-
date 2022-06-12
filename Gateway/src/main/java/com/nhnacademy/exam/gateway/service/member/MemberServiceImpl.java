package com.nhnacademy.exam.gateway.service.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.gateway.adapter.MemberAdapter;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.exception.CreateFailException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl
    implements MemberService {
    private final MemberAdapter memberAdapter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member findMemberById(String username) {
        return memberAdapter.findMemberById(username);
    }

    @Override
    public Member findMemberByMemberNo(Long memberNo) {
        return memberAdapter.findMemberByMemberNo(memberNo);
    }

    @Override
    public Long createMember(Member member) throws JsonProcessingException {
        member.setPw(passwordEncoder.encode(member.getPw()));
        String json = memberAdapter.createMember(member);

        Map<String, Long> map = getJsonDeserializedMapAndFailCheck(json);
        return map.get("memberNo");
    }

    private Map<String, Long> getJsonDeserializedMapAndFailCheck(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        failCheck(json, om);

        Map<String, Long> map = om.readValue(json,
            new TypeReference<Map<String, Long>>() {});
        return map;
    }

    private void failCheck(String json, ObjectMapper om) throws JsonProcessingException {
        String[] split = json.split(":");
        if (split[0].contains("fail")) {
            Map<String, String> map = om.readValue(json,
                new TypeReference<Map<String, String>>() {});
            throw new CreateFailException("create fail : becauese " + map.get("fail"));
        }
    }
}
