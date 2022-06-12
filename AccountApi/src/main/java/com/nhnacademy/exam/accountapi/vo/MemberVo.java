package com.nhnacademy.exam.accountapi.vo;

import lombok.Value;

@Value
public class MemberVo {
    private final String id;
    private final String pw;
    private final String email;
    private final String memberStatus;
    private final String authority;
}
