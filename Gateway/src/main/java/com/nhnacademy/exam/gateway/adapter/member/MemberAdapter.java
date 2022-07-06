package com.nhnacademy.exam.gateway.adapter.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.vo.member.MemberVoCreateComposition;
import java.util.List;

public interface MemberAdapter {
    Member findMemberByMemberNo(Long memberNo);

    Member findMemberById(String username);

    String createMember(Member member) throws JsonProcessingException;

    Member findByEmail(String email);

    List<MemberVoCreateComposition> findAllCreateComposition();
}
