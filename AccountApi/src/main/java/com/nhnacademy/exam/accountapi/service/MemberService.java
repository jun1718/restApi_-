package com.nhnacademy.exam.accountapi.service;

import com.nhnacademy.exam.accountapi.dto.MemberDto;
import com.nhnacademy.exam.accountapi.vo.MemberVo;

public interface MemberService {
    String createMember(MemberVo member);

    MemberDto findMemberDtoByMemberNo(Long memberNo);

    MemberDto findMemberDtoById(String id);

    MemberDto findMemberDtoByEmail(String email);
}
