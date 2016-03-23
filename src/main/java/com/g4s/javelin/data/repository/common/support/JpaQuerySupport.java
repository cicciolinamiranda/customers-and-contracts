package com.g4s.javelin.data.repository.common.support;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.PersistenceProvider;
import org.springframework.util.Assert;

import com.mysema.query.DefaultQueryMetadata;
import com.mysema.query.QueryMetadata;
import com.mysema.query.jpa.EclipseLinkTemplates;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.OpenJPATemplates;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.path.PathBuilder;

/**
 * Copy of org.springframework.data.jpa.repository.support.Querydsl (version 1.2.0 of spring-data-jpa)
 * with improvements to support creation of JPAQuery with QueryMetadata.
 *
 */
public class JpaQuerySupport extends AbstractJpqlQuerySupport {

    private final EntityManager em;
    private final PersistenceProvider provider;

    public JpaQuerySupport(final EntityManager em, final PathBuilder<?> builder) {
        super(builder);

        Assert.notNull(em);
        this.em = em;
        this.provider = PersistenceProvider.fromEntityManager(em);
    }

    public JPQLQuery createQuery() {
        return createQuery(new DefaultQueryMetadata());
    }

    public JPQLQuery createQuery(final EntityPath<?>... paths) {
        return createQuery(new DefaultQueryMetadata(), paths);
    }

    public JPQLQuery createQuery(final QueryMetadata queryMetadata, final EntityPath<?>... paths) {
        return createQuery(queryMetadata).from(paths);
    }

    public JPQLQuery createQuery(final QueryMetadata queryMetadata) {

        switch (provider) {
            case ECLIPSELINK:
                return new JPAQuery(em, EclipseLinkTemplates.DEFAULT, queryMetadata);

            case HIBERNATE:
                return new JPAQuery(em, HQLTemplates.DEFAULT, queryMetadata);

            case OPEN_JPA:
                return new JPAQuery(em, OpenJPATemplates.DEFAULT, queryMetadata);

            case GENERIC_JPA:
            default:
                return new JPAQuery(em, queryMetadata);
        }
    }

}
