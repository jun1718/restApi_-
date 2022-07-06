package com.nhnacademy.exam.taskapi.controller.project;

import com.nhnacademy.exam.taskapi.service.project.project.ProjectService;
import com.nhnacademy.exam.taskapi.vo.ProjectVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectCreateController {
    private final ProjectService projectService;

    @PostMapping(value = "/project/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createProject(@RequestBody ProjectVo projectVo) {
        return projectService.createProject(projectVo);
    }
}
