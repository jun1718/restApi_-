package com.nhnacademy.exam.gateway.controller.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.domain.project.Project;
import com.nhnacademy.exam.gateway.service.member.MemberService;
import com.nhnacademy.exam.gateway.service.project.ProjectService;
import com.nhnacademy.exam.gateway.vo.project.ProjectCreateSuccessResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProjectCreateController {
    private final ProjectService projectService;
    private final MemberService memberService;

    @PostMapping("/project/createProcessing")
    public ModelAndView createProcessing(@ModelAttribute Project project)
        throws JsonProcessingException {
        Long projectNo = projectService.createProject(project);
        Project resultProject = projectService.findProjectByProjectNo(projectNo);
        Member member = memberService.findMemberByMemberNo(project.getAdminMemberNo());
        ProjectCreateSuccessResponseVo responseVo = getProjectCreateSuccessResponseVo(resultProject, member);

        ModelAndView mav = new ModelAndView("project/showProjectCreateSuccess");
        mav.addObject("responseVo", responseVo);
        return mav;
    }

    private ProjectCreateSuccessResponseVo getProjectCreateSuccessResponseVo(Project resultProject,
                                                                             Member member) {
        ProjectCreateSuccessResponseVo responseVo = new ProjectCreateSuccessResponseVo(
            resultProject.getProjectNo(), resultProject.getAdminMemberNo(), member.getId(),
            resultProject.getName(), resultProject.getProjectStatus()
        );
        return responseVo;
    }
}
