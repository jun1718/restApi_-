package com.nhnacademy.exam.accountapi.controller;

import com.nhnacademy.exam.accountapi.dto.MemberDto;
import com.nhnacademy.exam.accountapi.entity.Member;
import com.nhnacademy.exam.accountapi.service.MemberService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberSelectController {
    private final MemberService memberService;

    @GetMapping("/{memberNo}")
    public MemberDto findMemberDtoByMemberNo(@PathVariable Long memberNo) {
        return memberService.findMemberDtoByMemberNo(memberNo);
    }

    @GetMapping
    public MemberDto findMemberDtoById(@RequestParam String id) {
        return memberService.findMemberDtoById(id);
    }

    @GetMapping("/email")
    public MemberDto findMemberDtoByEmail(@RequestParam String email) {
        return memberService.findMemberDtoByEmail(email);
    }
}
