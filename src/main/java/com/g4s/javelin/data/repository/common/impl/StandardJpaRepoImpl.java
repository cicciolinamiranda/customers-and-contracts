/*
 * Copyright (c) 2014-2015. KoolOkoy.
 * All rights reserved.
 */

package com.g4s.javelin.data.repository.common.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.StatelessSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.g4s.javelin.data.repository.common.JpaQueryExpression;
import com.g4s.javelin.data.repository.common.StandardJpaRepo;
import com.g4s.javelin.data.repository.common.support.HibernateQuerySupport;
import com.g4s.javelin.data.repository.common.support.JpaQuerySupport;
import com.g4s.javelin.data.repository.common.support.QueryMetadataSupport;
import com.google.common.collect.Lists;
import com.mysema.query.JoinExpression;
import com.mysema.query.QueryMetadata;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilder;

public class StandardJpaRepoImpl<T, ID extends Serializable> extends
        QueryDslExtJpaRepoImpl<T, ID> implements StandardJpaRepo<T, ID> {

    private static final Logger _LOG = LoggerFactory
            .getLogger(StandardJpaRepoImpl.class);

    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    private final JpaEntityInformation<T, ID> entityInfo;
    private final EntityPath<T> entityPath;

    private final QueryMetadataSupport queryMetadataSupport;
    private final JpaQuerySupport jpaQuerySupport;
    private final HibernateQuerySupport hibernateQuerySupport;

    public StandardJpaRepoImpl(final JpaEntityInformation<T, ID> entityInfo,
            final EntityManager em) {
        this(entityInfo, em, DEFAULT_ENTITY_PATH_RESOLVER);
    }

    public StandardJpaRepoImpl(final JpaEntityInformation<T, ID> entityInfo,
            final EntityManager em, final EntityPathResolver resolver) {
        super(entityInfo, em, resolver);

        this.entityInfo = entityInfo;
        this.entityPath = resolver.createPath(entityInfo.getJavaType());

        final PathBuilder<T> builder = new PathBuilder<T>(entityPath.getType(),
                entityPath.getMetadata());
        this.queryMetadataSupport = new QueryMetadataSupport(builder);
        this.jpaQuerySupport = new JpaQuerySupport(em, builder);
        this.hibernateQuerySupport = new HibernateQuerySupport(em, builder);
    }

    private QueryMetadata createFindAllQueryMetadata(
            final JpaQueryExpression query) {

        // Create query metadata
        final QueryMetadata queryMetadata = queryMetadataSupport
                .createQueryMetadata(entityPath);
        if (query != null) {
            queryMetadataSupport.applyJoins(queryMetadata, query.getJoins());
            queryMetadataSupport.applyOrders(queryMetadata, query.getOrders());
        }

        return queryMetadata;
    }

    private QueryMetadata createFindAllQueryMetadata(
            final JpaQueryExpression query, final OrderSpecifier<?>... orders) {

        // Create query metadata
        final QueryMetadata queryMetadata = queryMetadataSupport
                .createQueryMetadata(entityPath);

        // Note: We prioritize the ordering specified in the page request if any
        // as the more static select request order can be used as additional
        // sort order only
        queryMetadataSupport.applyOrders(queryMetadata,
                Lists.newArrayList(orders));
        if (query != null) {
            queryMetadataSupport.applyJoins(queryMetadata, query.getJoins());
            queryMetadataSupport.applyOrders(queryMetadata, query.getOrders());
        }

        return queryMetadata;
    }

    private QueryMetadata createFindAllQueryMetadata(
            final JpaQueryExpression query, final Pageable pageable) {

        // Create query metadata
        final QueryMetadata queryMetadata = queryMetadataSupport
                .createQueryMetadata(entityPath);

        // Note: We prioritize the ordering specified in the page request if any
        // as the more static select request order can be used as additional
        // sort order only
        queryMetadataSupport.applyPagination(queryMetadata, pageable);
        if (query != null) {
            queryMetadataSupport.applyJoins(queryMetadata, query.getJoins());
            queryMetadataSupport.applyOrders(queryMetadata, query.getOrders());
        }

        return queryMetadata;
    }

    private QueryMetadata createFindOneQueryMetadata(
            final JpaQueryExpression query) {

        // Create query metadata
        final QueryMetadata queryMetadata = queryMetadataSupport
                .createQueryMetadata(entityPath);
        if (query != null) {
            queryMetadataSupport.applyJoins(queryMetadata, query.getJoins());
        }

        return queryMetadata;
    }

    private JpaQueryExpression createJpaQueryExpression(
            final Predicate predicate) {
        return new JpaQueryExpression() {
            @Override
            public List<JoinExpression> getJoins() {
                return null;
            }

            @Override
            public List<OrderSpecifier<?>> getOrders() {
                return null;
            }

            @Override
            public Predicate getWhere() {
                return predicate;
            }

            @Override
            public boolean isDistinct() {
                return false;
            }
        };
    }

    private JpaQueryExpression createJpaQueryExpression(
            final Predicate predicate, final OrderSpecifier<?>... orders) {
        return new JpaQueryExpression() {
            @Override
            public List<JoinExpression> getJoins() {
                return null;
            }

            @Override
            public List<OrderSpecifier<?>> getOrders() {
                return Arrays.asList(orders);
            }

            @Override
            public Predicate getWhere() {
                return predicate;
            }

            @Override
            public boolean isDistinct() {
                return false;
            }
        };
    }

    @Override
    public Iterable<T> findAll(final JpaQueryExpression query) {

        // Create query metadata
        final QueryMetadata queryMetadata = createFindAllQueryMetadata(query);
        final Predicate predicate = query == null ? null : query.getWhere();

        // Create find query
        final JPQLQuery findQuery = jpaQuerySupport.createQuery(queryMetadata);
        jpaQuerySupport.applyPredicate(findQuery, predicate);

        final List<T> result = (query != null) && query.isDistinct() ? findQuery
                .distinct().list(entityPath) : findQuery.list(entityPath);

        _LOG.debug("Result of unordered findAll: resultCount={}, findQuery={}",
                result.size(), findQuery);
        return result;
    }

    @Override
    public Page<T> findAll(final JpaQueryExpression query,
            final Pageable pageable) {

        // Create query metadata
        final QueryMetadata queryMetadata = createFindAllQueryMetadata(query,
                pageable);
        final Predicate predicate = query == null ? null : query.getWhere();

        // Create count query
        final JPQLQuery countQuery = jpaQuerySupport.createQuery(entityPath);
        jpaQuerySupport.applyPredicate(countQuery, predicate);

        // Create find query
        final JPQLQuery findQuery = jpaQuerySupport.createQuery(queryMetadata);
        jpaQuerySupport.applyPredicate(findQuery, predicate);

        final long totalCount = (query != null) && query.isDistinct() ? countQuery
                .distinct().count() : countQuery.count();

        final List<T> result = (query != null) && query.isDistinct() ? findQuery
                .distinct().list(entityPath) : findQuery.list(entityPath);

        _LOG.debug(
                "Result of paginated findAll: resultCount={}, totalCount={}, findQuery={}",
                result.size(), totalCount, findQuery);
        return new PageImpl<T>(result, pageable, totalCount);
    }

    /**
     * IMPLEMENTATIONS FOR SelectExpressionJpaRepo
     */
    @Override
    public T findOne(final JpaQueryExpression query) {

        // Create query metadata
        final QueryMetadata queryMetadata = createFindOneQueryMetadata(query);
        final Predicate predicate = query == null ? null : query.getWhere();

        // Create query
        final JPQLQuery findQuery = jpaQuerySupport.createQuery(queryMetadata);
        jpaQuerySupport.applyPredicate(findQuery, predicate);

        final T result = findQuery.uniqueResult(entityPath);

        _LOG.debug("Result of findOne: result={}, findQuery={}", result,
                findQuery);
        return result;
    }

    @Override
    public long statelessCount() {
        return statelessCount((Predicate) null);
    }

    @Override
    public long statelessCount(final Predicate predicate) {
        // Open session
        final StatelessSession session = hibernateQuerySupport
                .openNewStatelessSession();

        // Create count query
        final JPQLQuery countQuery = hibernateQuerySupport.createQuery(session,
                entityPath);
        hibernateQuerySupport.applyPredicate(countQuery, predicate);

        final long result = countQuery.count();

        // Close session
        session.close();

        _LOG.debug("Result of statelessCount: result={}, countQuery={}",
                result, countQuery);
        return result;
    }

    @Override
    public boolean statelessExists(final ID id) {

        // Open session
        final StatelessSession session = hibernateQuerySupport
                .openNewStatelessSession();

        // Create query
        final JPQLQuery findQuery = hibernateQuerySupport.createQuery(session,
                (QueryMetadata) null);

        final boolean result = findQuery.exists();

        // Close session
        session.close();

        _LOG.debug("Result of statelessExists: result={}, id={}", result, id);
        return result;
    }

    @Override
    public Iterable<T> statelessFindAll() {
        return statelessFindAll((JpaQueryExpression) null);
    }

    @Override
    public Iterable<T> statelessFindAll(final JpaQueryExpression query) {

        // Create query metadata
        final QueryMetadata queryMetadata = createFindAllQueryMetadata(query);
        final Predicate predicate = query == null ? null : query.getWhere();

        // Open session
        final StatelessSession session = hibernateQuerySupport
                .openNewStatelessSession();

        // Create find query
        final JPQLQuery findQuery = hibernateQuerySupport.createQuery(session,
                queryMetadata);
        hibernateQuerySupport.applyPredicate(findQuery, predicate);

        final List<T> result = (query != null) && query.isDistinct() ? findQuery
                .distinct().list(entityPath) : findQuery.list(entityPath);

        // Close session
        session.close();

        _LOG.debug(
                "Result of unordered statelessFindAll: resultCount={}, findQuery={}",
                result.size(), findQuery);
        return result;
    }

    @Override
    public Page<T> statelessFindAll(final JpaQueryExpression query,
            final Pageable pageable) {

        // Create query metadata
        final QueryMetadata queryMetadata = createFindAllQueryMetadata(query,
                pageable);
        final Predicate predicate = query == null ? null : query.getWhere();

        // Open session
        final StatelessSession session = hibernateQuerySupport
                .openNewStatelessSession();

        // Create count query
        final JPQLQuery countQuery = hibernateQuerySupport.createQuery(session,
                entityPath);
        hibernateQuerySupport.applyPredicate(countQuery, predicate);

        // Create find query
        final JPQLQuery findQuery = hibernateQuerySupport.createQuery(session,
                queryMetadata);
        hibernateQuerySupport.applyPredicate(findQuery, predicate);

        final long totalCount = (query != null) && query.isDistinct() ? countQuery
                .distinct().count() : countQuery.count();

        final List<T> result = (query != null) && query.isDistinct() ? findQuery
                .distinct().list(entityPath) : findQuery.list(entityPath);

        // Close session
        session.close();

        _LOG.debug(
                "Result of paginated statelessFindAll: resultCount={}, totalCount={}, findQuery={}",
                result.size(), totalCount, findQuery);
        return new PageImpl<T>(result, pageable, totalCount);
    }

    @Override
    public Iterable<T> statelessFindAll(final OrderSpecifier<?>... orders) {
        return statelessFindAll(createJpaQueryExpression(null, orders));
    }

    @Override
    public Page<T> statelessFindAll(final Pageable pageable) {
        return statelessFindAll((JpaQueryExpression) null, pageable);
    }

    @Override
    public Iterable<T> statelessFindAll(final Predicate predicate) {
        return statelessFindAll(createJpaQueryExpression(predicate));
    }

    @Override
    public Iterable<T> statelessFindAll(final Predicate predicate,
            final OrderSpecifier<?>... orders) {
        return statelessFindAll(createJpaQueryExpression(predicate, orders));
    }

    @Override
    public Page<T> statelessFindAll(final Predicate predicate,
            final Pageable pageable) {
        return statelessFindAll(createJpaQueryExpression(predicate), pageable);
    }

    /**
     * IMPLEMENTATIONS FOR StatelessJpaRepo
     */

    @Override
    @SuppressWarnings("unchecked")
    public T statelessFindOne(final ID id) {
        // Open session
        final StatelessSession session = hibernateQuerySupport
                .openNewStatelessSession();

        final Object result = session.get(entityInfo.getJavaType(), id);

        // Close session
        session.close();

        _LOG.debug("Result of statelessFindOne: result={}, id={}", result, id);
        return (T) result;
    }

    @Override
    public T statelessFindOne(final JpaQueryExpression query) {

        // Create query metadata
        final QueryMetadata queryMetadata = createFindOneQueryMetadata(query);
        final Predicate predicate = query == null ? null : query.getWhere();

        // Open session
        final StatelessSession session = hibernateQuerySupport
                .openNewStatelessSession();

        // Create query
        final JPQLQuery findQuery = hibernateQuerySupport.createQuery(session,
                queryMetadata);
        hibernateQuerySupport.applyPredicate(findQuery, predicate);

        final T result = findQuery.uniqueResult(entityPath);

        // Close session
        session.close();

        _LOG.debug("Result of statelessFindOne: result={}, findQuery={}",
                result, findQuery);
        return result;
    }

    @Override
    public T statelessFindOne(final Predicate predicate) {
        return statelessFindOne(createJpaQueryExpression(predicate));
    }
}
