package com.nhnacademy.exam.gateway.vo.project;

import lombok.Value;

@Value
public class ProjectCreateSuccessResponseVo {
    private final Long projectNo;
    private Long adminMemberNo;
    private final String adminMemberId;
    private final String projectName;
    private final String projectStatus;
}
