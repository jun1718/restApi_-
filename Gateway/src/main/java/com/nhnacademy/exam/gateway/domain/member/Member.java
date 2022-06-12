package com.nhnacademy.exam.gateway.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long memberNo;
    private String id;
    private String pw;
    private String email;
    private String memberStatus;
    private String authority;
}
