package com.nhnacademy.exam.gateway.service.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.project.Project;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    Long createProject(Project project) throws JsonProcessingException;

    Project findProjectByProjectNo(Long projectNo);

//    List<Project> findByAdminMemberNo(Pageable pageable, Long memberNo);
    Page<Project> findByAdminMemberNo(Pageable pageable, Long memberNo);

    Page<Project> findByCompositionMemberNo(Pageable pageable, Long memberNo);
}
