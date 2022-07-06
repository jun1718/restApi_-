package com.nhnacademy.exam.accountapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.accountapi.entity.Member;
import com.nhnacademy.exam.accountapi.service.MemberService;
import com.nhnacademy.exam.accountapi.vo.MemberVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberCreateController.class)
class MemberCreateControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MemberService memberService;
    private MemberVo memberVo;
    @BeforeEach
    void setUp() {
        memberVo = new MemberVo("id", "pw", "email", "", "");
    }

    @Test
    void createMember() throws Exception {
        when(memberService.createMember(any()))
            .thenReturn("{\"memberNo\" : \"1\"}");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/member/create")
                .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(memberVo)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.memberNo", equalTo("1")));
    }
}