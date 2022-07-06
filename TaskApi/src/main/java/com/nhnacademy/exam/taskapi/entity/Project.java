package com.nhnacademy.exam.taskapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "project")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_no")
    private Long projectNo;
    @Column(name = "admin_member_no")
    private Long adminMemberNo;
    private String name;
    @Column(name = "project_status")
    private String projectStatus;

    public Project(Long adminMemberNo, String name, String projectStatus) {
        this.adminMemberNo = adminMemberNo;
        this.name = name;
        this.projectStatus = projectStatus;
    }
}
