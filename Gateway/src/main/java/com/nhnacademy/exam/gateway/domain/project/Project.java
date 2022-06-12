package com.nhnacademy.exam.gateway.domain.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private Long projectNo;
    private Long adminMemberNo;
    private String name;
    private String projectStatus;
}
