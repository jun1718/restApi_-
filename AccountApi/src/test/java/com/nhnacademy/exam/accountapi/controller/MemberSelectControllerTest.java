package com.nhnacademy.exam.accountapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.nhnacademy.exam.accountapi.dto.MemberDto;
import com.nhnacademy.exam.accountapi.dto.MemberDtoCreateComposition;
import com.nhnacademy.exam.accountapi.entity.Member;
import com.nhnacademy.exam.accountapi.service.MemberService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberSelectController.class)
class MemberSelectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private MemberDto memberDto;

    @BeforeEach
    void setUp() {
        memberDto = new MemberDto() {
            @Override
            public Long getMemberNo() {
                return 1L;
            }

            @Override
            public String getId() {
                return "hiId";
            }

            @Override
            public String getPw() {
                return "hiPw";
            }

            @Override
            public String getEmail() {
                return "hiEmail";
            }

            @Override
            public String getMemberStatus() {
                return "가입";
            }

            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
    }

    @MockBean
    private MemberService memberService;

    @Test
    void findMemberDtoByMemberNo() throws Exception {
        when(memberService.findMemberDtoByMemberNo(1L))
            .thenReturn(memberDto);

        mockMvc.perform(get("/members/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", equalTo("hiId")));
    }


    @Test
    void findMemberDtoById() throws Exception {
        when(memberService.findMemberDtoById("hiId"))
            .thenReturn(memberDto);

        mockMvc.perform(get("/members?id=hiId"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", equalTo("hiId")))
            .andExpect(jsonPath("$.email", equalTo("hiEmail")));
    }

    @Test
    void findAllCreateComposition() throws Exception {
        when(memberService.findAllCreateComposition())
            .thenReturn(List.of(new MemberDtoCreateComposition() {
                @Override
                public Long getMemberNo() {
                    return 1L;
                }

                @Override
                public String getId() {
                    return "dongmyo";
                }
            }));

        mockMvc.perform(get("/members/find/createComposition"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].memberNo", equalTo(1)))
            .andExpect(jsonPath("$[0].id", equalTo("dongmyo")));
    }

    @Test
    void findMemberDtoByEmail() throws Exception {
        when(memberService.findMemberDtoByEmail("hiEmail"))
            .thenReturn(memberDto);

        mockMvc.perform(get("/members/email?email=" + "hiEmail"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", equalTo("hiId")));
    }
}