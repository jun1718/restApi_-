package com.nhnacademy.exam.taskapi.repository.composition;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDtoClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

// TODO 2: 이거
public interface CompositionRepositoryCustom {
    Page<ProjectDtoClass> findProjectDtoByMemberNo(Long memberNo, Pageable pageable);
}
