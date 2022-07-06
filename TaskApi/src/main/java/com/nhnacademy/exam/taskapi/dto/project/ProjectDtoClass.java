package com.nhnacademy.exam.taskapi.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoClass {
    private Long projectNo;
    private Long adminMemberNo;
    private String name;
    private String projectStatus;
}
