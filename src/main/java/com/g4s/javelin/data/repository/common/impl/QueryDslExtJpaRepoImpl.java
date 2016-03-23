/*
 * Copyright (c) 2014-2015. KoolOkoy.
 * All rights reserved.
 */
package com.g4s.javelin.data.repository.common.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.g4s.javelin.data.repository.common.QueryDslExtJpaRepo;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.FactoryExpression;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilder;

/**
 * Extension to QueryDslJpaRepository to be able to support use of projections
 * in query dsl. Implementation patterned from:
 * http://answer.techwikihow.com/497252
 * /spring-data-querydsl-fetch-subset-columns-beanconstructor-projection.html
 */
public class QueryDslExtJpaRepoImpl<T, ID extends Serializable> extends
        QueryDslJpaRepository<T, ID> implements QueryDslExtJpaRepo<T> {

    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    private final EntityPath<T> path;
    private final PathBuilder<T> builder;
    private final Querydsl querydsl;

    public QueryDslExtJpaRepoImpl(
            final JpaEntityInformation<T, ID> entityInformation,
            final EntityManager entityManager) {
        this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
    }

    public QueryDslExtJpaRepoImpl(
            final JpaEntityInformation<T, ID> entityInformation,
            final EntityManager entityManager, final EntityPathResolver resolver) {
        super(entityInformation, entityManager, resolver);

        this.path = resolver.createPath(entityInformation.getJavaType());
        this.builder = new PathBuilder<T>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
    }

    public Iterable<T> findAll(final FactoryExpression<T> factoryExpression,
            final Predicate predicate) {
        return createQuery(predicate).list(factoryExpression);
    }

    public Iterable<T> findAll(final FactoryExpression<T> factoryExpression,
            final Predicate predicate,
            final OrderSpecifier<?>... orderSpecifiers) {
        return createQuery(predicate).orderBy(orderSpecifiers).list(path);
    }

    public Page<T> findAll(final FactoryExpression<T> factoryExpression,
            final Predicate predicate, final Pageable pageable) {
        final JPQLQuery countQuery = createQuery(predicate);
        final JPQLQuery query = querydsl.applyPagination(pageable,
                createQuery(predicate));

        final Long total = countQuery.count();
        final List<T> content = total > pageable.getOffset() ? query
                .list(factoryExpression) : Collections.<T>emptyList();

        return new PageImpl<T>(content, pageable, total);
    }
}
