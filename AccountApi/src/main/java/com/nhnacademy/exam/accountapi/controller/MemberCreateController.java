package com.nhnacademy.exam.accountapi.controller;

import com.nhnacademy.exam.accountapi.entity.Member;
import com.nhnacademy.exam.accountapi.service.MemberService;
import com.nhnacademy.exam.accountapi.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberCreateController {
    private final MemberService memberService;

    @PostMapping(value = "/member/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createMember(@RequestBody MemberVo memberVo) {
        return memberService.createMember(memberVo);
    }
}
