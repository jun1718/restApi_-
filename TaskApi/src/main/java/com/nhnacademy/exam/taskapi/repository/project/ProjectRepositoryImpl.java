//package com.nhnacademy.exam.taskapi.repository.project;
//
//import com.nhnacademy.exam.taskapi.dto.project.ProjectDtoClass;
//import com.nhnacademy.exam.taskapi.entity.Project;
//import com.nhnacademy.exam.taskapi.entity.QComposition;
//import com.nhnacademy.exam.taskapi.entity.QProject;
//import com.querydsl.core.QueryResults;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.JPQLQuery;
//import com.querydsl.jpa.impl.JPAQuery;
//import javax.servlet.http.Cookie;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//public class ProjectRepositoryImpl extends QuerydslRepositorySupport
//    implements ProjectRepositoryCustom {
//    public ProjectRepositoryImpl() {
//        super(Project.class);
//    }
//
//    @Override
//    public String live(Pageable pageable, int memberNo) {
//        QProject qProject = QProject.project;
//        QComposition qComposition = QComposition.composition;
//
//        JPQLQuery query = from(qProject)
//            .innerJoin(qProject, qComposition.project)
//            .where(qComposition.memberNo.eq(Long.valueOf(memberNo)))
//            .offset(pageable.getPageNumber())
//            .limit(pageable.getPageSize())
//            .orderBy(qProject.projectNo.desc())
//            .select(Projections.bean(ProjectDtoClass.class, qProject.projectNo, qProject.projectStatus, qProject.adminMemberNo));
//        QueryResults queryResults = query.fetchResults();
//        PageImpl<ProjectDtoClass> page =new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
//        return null;
//    }
//}
