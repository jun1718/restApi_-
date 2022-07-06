package com.nhnacademy.exam.gateway.adapter.project;

import com.nhnacademy.exam.gateway.domain.project.Project;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectAdapter {
    String createProject(Project project);

    Project findProjectByProjectNo(Long projectNo);

    Page<Project> findByAdminMemberNo(Pageable pageable, Long memberNo);
    Page<Project> findByCompositionMemberNo(Pageable pageable, Long memberNo);
}
