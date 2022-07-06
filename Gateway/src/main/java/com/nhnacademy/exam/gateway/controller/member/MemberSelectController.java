package com.nhnacademy.exam.gateway.controller.member;

import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberSelectController {

    @Qualifier("memberServiceImpl2")
    private final MemberService memberService;

    @GetMapping("/{memberNo}")
    public ModelAndView getMember(@PathVariable Long memberNo) {
        Member member = memberService.findMemberByMemberNo(memberNo);
        ModelAndView mav = new ModelAndView("showMember");
        mav.addObject(member);
        return mav;
    }
}
