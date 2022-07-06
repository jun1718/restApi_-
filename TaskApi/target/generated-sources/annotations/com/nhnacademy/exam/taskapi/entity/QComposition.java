package com.nhnacademy.exam.taskapi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComposition is a Querydsl query type for Composition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComposition extends EntityPathBase<Composition> {

    private static final long serialVersionUID = 2061173342L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComposition composition = new QComposition("composition");

    public final NumberPath<Long> compositionNo = createNumber("compositionNo", Long.class);

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final QProject project;

    public QComposition(String variable) {
        this(Composition.class, forVariable(variable), INITS);
    }

    public QComposition(Path<? extends Composition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComposition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComposition(PathMetadata metadata, PathInits inits) {
        this(Composition.class, metadata, inits);
    }

    public QComposition(Class<? extends Composition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

