package com.nhnacademy.exam.taskapi.controller.project;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDto;
import com.nhnacademy.exam.taskapi.dto.project.ProjectDtoClass;
import com.nhnacademy.exam.taskapi.entity.Project;
import com.nhnacademy.exam.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.exam.taskapi.service.composition.CompositionService;
import com.nhnacademy.exam.taskapi.service.project.project.ProjectService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProjectSelectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;
    @MockBean
    private CompositionService compositionService;
    private Project project;

    private ProjectDto projectDto;

    @BeforeEach
    void setUp() {
        project = new Project(1L, "project1", "활성");

        projectDto = new ProjectDto() {
            @Override
            public Long getProjectNo() {
                return 1000L;
            }

            @Override
            public Long getAdminMemberNo() {
                return 1000L;
            }

            @Override
            public String getName() {
                return "1000name";
            }

            @Override
            public String getProjectStatus() {
                return "활성";
            }
        };
    }

    @Test
    void findProjectDtoByProjectNo() throws Exception {
        when(projectService.findProjectDtoByProjectNo(any()))
            .thenReturn(projectDto);

        mockMvc.perform(get("/projects/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.adminMemberNo", equalTo(1000)))
            .andExpect(jsonPath("$.name", equalTo("1000name")));
    }

    @Test
    void findProjectDtoClassByAdminMemberNo() throws Exception {
        Pageable pageable = PageRequest.of(0, 3);
        PageImpl<ProjectDto> page = new PageImpl<>(List.of(), pageable, 200);

        when(projectService.findPageByAdminMemberNo(any(), any()))
            .thenReturn(page);

        mockMvc.perform(get("/projects/findProjectDtoClassByAdminMemberNo/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalElements", equalTo(200)));
    }

    @Test
    void findProjectDtoClassByComposition() throws Exception {
        Pageable pageable = PageRequest.of(0, 3);
        PageImpl<ProjectDtoClass> page = new PageImpl<>(List.of(), pageable, 200);

        when(compositionService.findProjectDtoByComposition(any(), any()))
            .thenReturn(page);

        mockMvc.perform(get("/projects/findProjectDtoClassByComposition/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.totalElements", equalTo(200)));
    }
}