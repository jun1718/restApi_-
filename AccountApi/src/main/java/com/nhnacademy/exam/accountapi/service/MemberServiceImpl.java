package com.nhnacademy.exam.accountapi.service;

import com.nhnacademy.exam.accountapi.dto.MemberDto;
import com.nhnacademy.exam.accountapi.dto.MemberDtoCreateComposition;
import com.nhnacademy.exam.accountapi.entity.Member;
import com.nhnacademy.exam.accountapi.repository.MemberRepository;
import com.nhnacademy.exam.accountapi.vo.MemberVo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl
    implements MemberService {
    private final MemberRepository memberRepository;

    private static final String FAIL_VALUE_DUPLICATION = "{\"fail\" : \"duplication\"}";
    private static final String FAIL_VALUE_NOT_FOUND = "{\"fail\" : \"notFoundMember\"}";

    @Override
    public String createMember(MemberVo memberVo) {
        String id = memberVo.getId();
        String email = memberVo.getEmail();
        if (isDuplication(id, email)) return FAIL_VALUE_DUPLICATION;

        Member member = getMember(memberVo);
        memberRepository.save(member);
        return "{\"memberNo\" : \"" + member.getMemberNo() + "\"}";
    }

    private boolean isDuplication(String id, String email) {
        return memberRepository.existsById(id)
            || memberRepository.existsByEmail(email);
    }

    private Member getMember(MemberVo memberVo) {
        Member member = new Member(memberVo.getId(), memberVo.getPw(), memberVo.getEmail(),
            memberVo.getMemberStatus(), memberVo.getAuthority());
        return member;
    }


    @Override
    public MemberDto findMemberDtoByMemberNo(Long memberNo) {
        return memberRepository.findMemberDtoByMemberNo(memberNo);
    }

    @Override
    public MemberDto findMemberDtoById(String id) {
        return memberRepository.findMemberDtoById(id);
    }

    @Override
    public MemberDto findMemberDtoByEmail(String email) {
        return memberRepository.findMemberDtoByEmail(email);
    }

    @Override
    public List<MemberDtoCreateComposition> findAllCreateComposition() {
        List<MemberDtoCreateComposition> memberDtoCreateCompositionAll =
            memberRepository.findAllCreateComposition();
        return memberDtoCreateCompositionAll;
    }
}
