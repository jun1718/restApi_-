package com.nhnacademy.exam.taskapi.controller.project;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.taskapi.entity.Composition;
import com.nhnacademy.exam.taskapi.entity.Project;
import com.nhnacademy.exam.taskapi.service.project.project.ProjectService;
import com.nhnacademy.exam.taskapi.vo.ProjectVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProjectCreateController.class)
class ProjectCreateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    private ProjectVo projectVo;

    @BeforeEach
    void setUp() {
        projectVo = new ProjectVo(1L, 1L, "jor", "활성");
    }

    @Test
    void createProject() throws Exception {
        when(projectService.createProject(any()))
            .thenReturn("{\"projectNo\" : \"1\"}");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/project/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(projectVo)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.projectNo", equalTo("1")));
    }
}