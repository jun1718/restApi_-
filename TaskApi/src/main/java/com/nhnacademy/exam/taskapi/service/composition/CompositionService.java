package com.nhnacademy.exam.taskapi.service.composition;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDtoClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompositionService {
    Page<ProjectDtoClass> findProjectDtoByComposition(Long memberNo, Pageable pageable);
}
