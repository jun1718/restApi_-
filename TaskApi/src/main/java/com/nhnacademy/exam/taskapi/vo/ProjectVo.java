package com.nhnacademy.exam.taskapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectVo {
    private Long projectNo;
    private Long adminMemberNo;
    private String name;
    private String projectStatus;
}
