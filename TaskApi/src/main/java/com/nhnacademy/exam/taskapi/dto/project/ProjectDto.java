package com.nhnacademy.exam.taskapi.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface  ProjectDto {
    Long getProjectNo();
    Long getAdminMemberNo();
    String getName();
    String getProjectStatus();
}
