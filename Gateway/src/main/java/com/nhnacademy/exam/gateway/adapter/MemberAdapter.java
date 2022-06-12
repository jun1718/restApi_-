package com.nhnacademy.exam.gateway.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.member.Member;

public interface MemberAdapter {
    Member findMemberByMemberNo(Long memberNo);

    Member findMemberById(String username);

    String createMember(Member member) throws JsonProcessingException;

    Member findByEmail(String email);
}
