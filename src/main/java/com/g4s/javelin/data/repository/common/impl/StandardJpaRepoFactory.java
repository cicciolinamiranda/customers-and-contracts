/*
 * Copyright (c) 2014-2015. KoolOkoy.
 * All rights reserved.
 */

package com.g4s.javelin.data.repository.common.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy;
import org.springframework.data.jpa.repository.query.QueryExtractor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.LockModeRepositoryPostProcessor;
import org.springframework.data.jpa.repository.support.PersistenceProvider;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.QueryDslUtils;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.util.Assert;

import com.g4s.javelin.data.repository.common.StandardJpaRepo;

/**
 * Copy of org.springframework.data.jpa.repository.support.JpaRepositoryFactory
 * with extensions to support creation of different repository types including
 * the custom jpa repository.
 */
public class StandardJpaRepoFactory extends RepositoryFactorySupport {

    private static final Logger _LOG = LoggerFactory
            .getLogger(StandardJpaRepoFactory.class);

    // Supported repository implementations from most specific to most generic
    private static final int REPOSITORY_STANDARD = 3;
    private static final int REPOSITORY_QUERY_DSL = 2;
    private static final int REPOSITORY_SIMPLE_JPA = 1;

    private final EntityManager entityManager;
    private final QueryExtractor extractor;
    private final LockModeRepositoryPostProcessor lockModePostProcessor;

    public StandardJpaRepoFactory(final EntityManager entityManager) {

        Assert.notNull(entityManager);
        this.entityManager = entityManager;
        this.extractor = PersistenceProvider.fromEntityManager(entityManager);
        this.lockModePostProcessor = LockModeRepositoryPostProcessor.INSTANCE;

        addRepositoryProxyPostProcessor(lockModePostProcessor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, ID extends Serializable> JpaEntityInformation<T, ID> getEntityInformation(
            final Class<T> domainClass) {
        return (JpaEntityInformation<T, ID>) JpaEntityInformationSupport
                .getMetadata(domainClass, entityManager);
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(
            final QueryLookupStrategy.Key key) {
        return JpaQueryLookupStrategy.create(entityManager, key, extractor);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(final RepositoryMetadata metadata) {
        switch (getRepositoryType(metadata.getRepositoryInterface())) {
            case REPOSITORY_STANDARD:
                return StandardJpaRepoImpl.class;

            case REPOSITORY_QUERY_DSL:
                return QueryDslJpaRepository.class;

            case REPOSITORY_SIMPLE_JPA:
            default:
                return SimpleJpaRepository.class;
        }
    }

    private int getRepositoryType(final Class<?> repositoryInterface) {
        // NOTE: Conditions should be ordered from most specific to most generic
        if (QueryDslUtils.QUERY_DSL_PRESENT
                && StandardJpaRepo.class.isAssignableFrom(repositoryInterface)) {
            return REPOSITORY_STANDARD;

        } else
            if (QueryDslUtils.QUERY_DSL_PRESENT
                    && QueryDslPredicateExecutor.class
                            .isAssignableFrom(repositoryInterface)) {
                return REPOSITORY_QUERY_DSL;

            } else {
                return REPOSITORY_SIMPLE_JPA;
            }
    }

    @Override
    protected Object getTargetRepository(final RepositoryMetadata metadata) {
        return getTargetRepository(metadata, entityManager);
    }

    protected <T, ID extends Serializable> JpaRepository<?, ?> getTargetRepository(
            final RepositoryMetadata metadata, final EntityManager em) {
        final SimpleJpaRepository<?, ?> repo = instantiateJpaRepository(
                metadata, em);
        repo.setLockMetadataProvider(lockModePostProcessor
                .getLockMetadataProvider());
        return repo;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected SimpleJpaRepository<?, ?> instantiateJpaRepository(
            final RepositoryMetadata metadata, final EntityManager em) {
        final Class<?> repositoryInterface = metadata.getRepositoryInterface();
        final JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata
                .getDomainType());

        switch (getRepositoryType(repositoryInterface)) {
            case REPOSITORY_STANDARD:
                _LOG.debug("Creating ExistJpaRepoImpl for: {}",
                        repositoryInterface);
                return new StandardJpaRepoImpl(entityInformation, em);

            case REPOSITORY_QUERY_DSL:
                _LOG.debug("Creating QueryDslJpaRepository for: {}",
                        repositoryInterface);
                return new QueryDslJpaRepository(entityInformation, em);

            case REPOSITORY_SIMPLE_JPA:
            default:
                _LOG.debug("Creating SimpleJpaRepository for: {}",
                        repositoryInterface);
                return new SimpleJpaRepository(entityInformation, em);
        }
    }

}
