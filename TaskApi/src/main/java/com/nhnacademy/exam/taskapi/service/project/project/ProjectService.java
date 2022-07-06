package com.nhnacademy.exam.taskapi.service.project.project;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDto;
import com.nhnacademy.exam.taskapi.vo.ProjectVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    String createProject(ProjectVo projectVo);

    ProjectDto findProjectDtoByProjectNo(Long projectNo);

    Page<ProjectDto> findPageByAdminMemberNo(Pageable pageable, Long memberNo);
}
