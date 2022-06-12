package com.nhnacademy.exam.gateway.service.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.gateway.adapter.member.MemberAdapter;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.exception.CreateFailException;
import com.nhnacademy.exam.gateway.gateEnum.member.MemberAuthorityEnum;
import com.nhnacademy.exam.gateway.gateEnum.common.StatusEnum;
import com.nhnacademy.exam.gateway.service.common.CreateDeserializer;
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
        fixMemberAboutEncryptionAndEtc(member);

        String json = memberAdapter.createMember(member);
        Map<String, Long> map = CreateDeserializer.getJsonDeserializedMapAndFailCheck(json);

        return map.get("memberNo");
    }

    private void fixMemberAboutEncryptionAndEtc(Member member) {
        member.setPw(passwordEncoder.encode(member.getPw()));
        member.setMemberStatus(StatusEnum.JOIN.getValue());

        String authority =
            MemberAuthorityEnum.getAuthorityThroughParameter(member.getAuthority());
        member.setAuthority(authority);
    }
}
