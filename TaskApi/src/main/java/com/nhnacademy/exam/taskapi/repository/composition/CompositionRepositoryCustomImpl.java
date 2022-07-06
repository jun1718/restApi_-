package com.nhnacademy.exam.taskapi.repository.composition;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDtoClass;
import com.nhnacademy.exam.taskapi.entity.Composition;
import com.nhnacademy.exam.taskapi.entity.QComposition;
import com.nhnacademy.exam.taskapi.entity.QProject;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class CompositionRepositoryCustomImpl extends QuerydslRepositorySupport
    implements CompositionRepositoryCustom {
    public CompositionRepositoryCustomImpl() {
        super(Composition.class);
    }

    @Override
    public PageImpl<ProjectDtoClass> findProjectDtoByMemberNo(Long memberNo, Pageable pageable) {
        QComposition qComposition = QComposition.composition;
        QProject qProject = QProject.project;

        int pageSize = pageable.getPageSize();
        JPQLQuery query = from(qComposition)
            .innerJoin(qComposition.project, qProject)
            .where(qComposition.memberNo.eq(memberNo))
            .limit(pageSize)
            .offset(pageable.getPageNumber() * pageSize)
            .orderBy(qProject.projectNo.desc())
            .select(Projections.bean(ProjectDtoClass.class, qProject.projectNo, qProject.adminMemberNo,
                qProject.name, qProject.projectStatus));

        QueryResults queryResults = query.fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
