package com.nhnacademy.exam.taskapi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "composition")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "composition_no")
    private Long compositionNo;

    @Column(name = "member_no")
    private Long memberNo;

    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    public Composition(Long memberNo, Project project) {
        this.memberNo = memberNo;
        this.project = project;
    }
}
