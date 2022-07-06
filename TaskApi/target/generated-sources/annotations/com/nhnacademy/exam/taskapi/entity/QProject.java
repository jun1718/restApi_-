package com.nhnacademy.exam.taskapi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = -401818163L;

    public static final QProject project = new QProject("project");

    public final NumberPath<Long> adminMemberNo = createNumber("adminMemberNo", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> projectNo = createNumber("projectNo", Long.class);

    public final StringPath projectStatus = createString("projectStatus");

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

