package com.nhnacademy.exam.gateway.vo.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVo {
    private Long memberNo;
    private String id;
    private String email;
    private String memberStatus;
    private String authority;
}
