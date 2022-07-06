package com.nhnacademy.exam.taskapi.service.project.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.nhnacademy.exam.taskapi.controller.project.ProjectSelectController;
import com.nhnacademy.exam.taskapi.dto.project.ProjectDto;
import com.nhnacademy.exam.taskapi.repository.composition.CompositionRepository;
import com.nhnacademy.exam.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.exam.taskapi.service.composition.CompositionService;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest({ProjectService.class, CompositionService.class}) // compositionservice는 학습용, beanfactory에 이것도 추가하는것임
class ProjectServiceImplTest {
    @Autowired
//    @Qualifier(value = "projectServiceImpl")
    private ProjectService projectService;

    @Autowired
    private CompositionService compositionService; // 학습용 autowired

    @MockBean
    private CompositionRepository compositionRepository;

    @MockBean
    private ProjectRepository projectRepository;
//    private ProjectService projectService = new ProjectServiceImpl(projectRepository);

    @Test
    void findProjectDtoByProjectNo() {
        Pageable pageRequest =
        PageRequest.of(0, 10);

        PageImpl<ProjectDto> page = new PageImpl<>(Collections.emptyList(), pageRequest, 3);
        when(projectRepository.findPageByAdminMemberNo(1L, pageRequest))
            .thenReturn(page);

        Page<ProjectDto> pageResult = projectService.findPageByAdminMemberNo(pageRequest, 1L);

        assertThat(pageResult.getContent())
            .isEqualTo(Collections.emptyList());
        assertThat(pageResult.getContent())
            .isEqualTo(page.getContent());
        assertThat(pageResult)
            .isEqualTo(page);

//        WebApplicationContext webApplicationContext =;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


    }
}