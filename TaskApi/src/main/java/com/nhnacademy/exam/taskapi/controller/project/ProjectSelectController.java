package com.nhnacademy.exam.taskapi.controller.project;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDto;
import com.nhnacademy.exam.taskapi.dto.project.ProjectDtoClass;
import com.nhnacademy.exam.taskapi.service.composition.CompositionService;
import com.nhnacademy.exam.taskapi.service.project.project.ProjectService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor

@Data
public class ProjectSelectController {
    private final ProjectService projectService;
    private final CompositionService compositionService;

    @GetMapping("/{projectNo}")
    public ProjectDto findProjectDtoByProjectNo(@PathVariable Long projectNo) {
        return projectService.findProjectDtoByProjectNo(projectNo);
    }


    @GetMapping("/findProjectDtoClassByAdminMemberNo/{adminMemberNo}")
    public Page<ProjectDto> findProjectDtoClassByAdminMemberNo(Pageable pageable, @PathVariable Long adminMemberNo) {
        Page<ProjectDto> pageByAdminMemberNo = projectService.findPageByAdminMemberNo(pageable, adminMemberNo);
        return pageByAdminMemberNo;
    }

    @GetMapping("/findProjectDtoClassByComposition/{memberNo}")
    public Page<ProjectDtoClass> findProjectDtoClassByComposition(Pageable pageable, @PathVariable Long memberNo) {
        return compositionService.findProjectDtoByComposition(memberNo, pageable);
    }
}
