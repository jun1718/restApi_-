package com.nhnacademy.exam.gateway.controller.project;

import com.nhnacademy.exam.gateway.common.FieldRepository;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.domain.project.Project;
import com.nhnacademy.exam.gateway.service.member.MemberService;
import com.nhnacademy.exam.gateway.service.project.ProjectService;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import com.nhnacademy.exam.gateway.vo.project.ProjectCreateSuccessResponseVo;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectSelectController {
    private final ProjectService projectService;
    private final RedisTemplate<String, MemberVo> redisTemplate;
    private final FieldRepository fieldRepository;
    private final MemberService memberService;

    @GetMapping("/admin")
    public ModelAndView showProjectAllAdmin(Pageable pageable) {
        MemberVo memberVo =
            (MemberVo) redisTemplate.opsForHash().get(fieldRepository.getSessionId(), "member");

        Page<Project> page = projectService.findByAdminMemberNo(pageable, memberVo.getMemberNo());
        ModelAndView mav = new ModelAndView("project/showProjectAllAdmin");
        mav.addObject("projectList", page.getContent());
        mav.addObject("totalPages", page.getTotalPages());
        mav.addObject("pageable", pageable);
        return mav;
    }

    @GetMapping("/composition")
    public ModelAndView showProjectAllComposition(Pageable pageable) {
        MemberVo memberVo =
            (MemberVo) redisTemplate.opsForHash().get(fieldRepository.getSessionId(), "member");

        Page<Project> page = projectService.findByCompositionMemberNo(pageable, memberVo.getMemberNo());
        ModelAndView mav = new ModelAndView("project/showProjectAllComposition");
        mav.addObject("projectList", page.getContent());
        mav.addObject("totalPages", page.getTotalPages());
        mav.addObject("pageable", pageable);
        return mav;
    }

    @GetMapping("/management/{projectNo}")
    public String showManagement(@PathVariable Long projectNo, HttpSession session) {
        Project project = projectService.findProjectByProjectNo(projectNo);

        session.setAttribute("project", project);
        MemberVo memberVo = (MemberVo) redisTemplate.opsForHash().get(fieldRepository.getSessionId(), "member");
        session.setAttribute("member", memberVo);
        return "project/showManagement";
    }
}
