package com.nhnacademy.exam.taskapi.repository.project;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDto;
import com.nhnacademy.exam.taskapi.entity.Project;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByName(String name);
    boolean existsByProjectNo(Long projectNo);

    ProjectDto findProjectDtoByProjectNo(Long projectNo);

    List<ProjectDto> findAllBy(org.springframework.data.domain.Pageable pageable);
    Page<ProjectDto> findPageByAdminMemberNo(Long adminMemberNo, Pageable pageable);
}
