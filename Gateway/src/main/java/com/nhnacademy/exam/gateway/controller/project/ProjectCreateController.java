package com.nhnacademy.exam.gateway.controller.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.project.Project;
import com.nhnacademy.exam.gateway.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProjectCreateController {
    private final ProjectService projectService;

    @PostMapping("/project/createProcessing")
    public ModelAndView createProcessing(@ModelAttribute Project project)
        throws JsonProcessingException {
        Long projectNo = projectService.createProject(project);
        Project resultProject = projectService.findProjectByProjectNo(projectNo);

        ModelAndView mav = new ModelAndView("project/showProjectCreateSuccess");
        mav.addObject("project", resultProject);
        return mav;
    }
}
