package com.nhnacademy.exam.gateway.service.member;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.vo.member.MemberVoCreateComposition;
import java.util.List;

public interface MemberService {
    Member findMemberById(String username);
    Member findMemberByMemberNo(Long memberNo);
    Long createMember(Member member) throws JsonProcessingException;

    List<MemberVoCreateComposition> findAllCreateComposition();
}
