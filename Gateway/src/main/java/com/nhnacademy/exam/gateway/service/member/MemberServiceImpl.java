package com.nhnacademy.exam.gateway.service.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.adapter.member.MemberAdapter;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.gateEnum.member.MemberAuthorityEnum;
import com.nhnacademy.exam.gateway.gateEnum.common.StatusEnum;
import com.nhnacademy.exam.gateway.service.common.CreateDeserializer;
import com.nhnacademy.exam.gateway.vo.member.MemberVoCreateComposition;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Override
    public List<MemberVoCreateComposition> findAllCreateComposition() {
        return memberAdapter.findAllCreateComposition();
    }

    private void fixMemberAboutEncryptionAndEtc(Member member) {
        member.setPw(passwordEncoder.encode(member.getPw()));
        member.setMemberStatus(StatusEnum.JOIN.getValue());

        String authority =
            MemberAuthorityEnum.getAuthorityThroughParameter(member.getAuthority());
        member.setAuthority(authority);
    }
}
