package com.nhnacademy.exam.gateway.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.gateEnum.member.MemberAuthorityEnum;
import com.nhnacademy.exam.gateway.service.member.MemberService;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberCreateController {
    private final MemberService memberService;

    @PostMapping("/createProcessing")
    public ModelAndView createMember(@ModelAttribute Member member) throws JsonProcessingException {
        Long memberNo = memberService.createMember(member);
        Member resultMember = memberService.findMemberByMemberNo(memberNo);

        ModelAndView mav = new ModelAndView("member/showMemberCreateSuccess");
        mav.addObject("member", new MemberVo(resultMember.getMemberNo(), resultMember.getId(),
            resultMember.getEmail(), resultMember.getMemberStatus(), resultMember.getAuthority()));
        return mav;
    }
}
