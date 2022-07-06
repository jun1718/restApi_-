package com.nhnacademy.exam.gateway.controller.composition;

import com.nhnacademy.exam.gateway.common.FieldRepository;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.domain.project.Project;
import com.nhnacademy.exam.gateway.service.component.CompositionService;
import com.nhnacademy.exam.gateway.service.member.MemberService;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import com.nhnacademy.exam.gateway.vo.member.MemberVoCreateComposition;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/composition")
public class CompositionCreateController {
    private final CompositionService compositionService;
    private final MemberService memberService;
    private final RedisTemplate redisTemplate;
    private final FieldRepository fieldRepository;

    @GetMapping("/create")
    public ModelAndView createComposition(HttpSession session) {
        List<MemberVoCreateComposition> memberList = memberService.findAllCreateComposition();
        ModelAndView mav = new ModelAndView("composition/showCompositionCreateForm");

        Project project = (Project) session.getAttribute("project");
        MemberVo user =
            (MemberVo) redisTemplate.opsForHash().get(fieldRepository.getSessionId(), "member");

        if (!Objects.equals(project.getAdminMemberNo(), user.getMemberNo())) return new ModelAndView("showHome");
        mav.addObject("memberList", memberList);
        return mav;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleException(Exception ex, Model model) {
        log.error("exception 발생 : {}", ex.getMessage());
        model.addAttribute("msg", "잠시후 다시 시도해주세요");
        return "error/error";
    }
}
