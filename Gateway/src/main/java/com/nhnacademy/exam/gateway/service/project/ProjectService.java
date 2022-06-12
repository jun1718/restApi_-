package com.nhnacademy.exam.gateway.service.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.exam.gateway.domain.project.Project;

public interface ProjectService {
    Long createProject(Project project) throws JsonProcessingException;

    Project findProjectByProjectNo(Long projectNo);
}
