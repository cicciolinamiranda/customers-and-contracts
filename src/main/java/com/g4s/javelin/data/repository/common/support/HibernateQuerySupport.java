package com.g4s.javelin.data.repository.common.support;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.Validate;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.springframework.data.jpa.repository.support.PersistenceProvider;
import org.springframework.util.Assert;

import com.mysema.query.DefaultQueryMetadata;
import com.mysema.query.QueryMetadata;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.DefaultSessionHolder;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.hibernate.StatelessSessionHolder;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.path.PathBuilder;

public class HibernateQuerySupport extends AbstractJpqlQuerySupport {

    private final EntityManager em;

    public HibernateQuerySupport(final EntityManager em, final PathBuilder<?> builder) {
        super(builder);

        Assert.notNull(em);
        this.em = em;

        // Verify provider
        PersistenceProvider provider = PersistenceProvider.fromEntityManager(em);
        Validate.isTrue(PersistenceProvider.HIBERNATE == provider, "Only PersistenceProvider.HIBERNATE "
                + "is supported in HibernateQuerySupport: found=" + provider);
    }

    /**
     * STATEFUL SESSION OPERATIONS
     */
    public JPQLQuery createQuery(final Session session) {
        return createQuery(session, new DefaultQueryMetadata());
    }

    public JPQLQuery createQuery(final Session session, final EntityPath<?>... paths) {
        return createQuery(session, new DefaultQueryMetadata(), paths);
    }

    public JPQLQuery createQuery(final Session session, final QueryMetadata queryMetadata, final EntityPath<?>... paths) {
        return createQuery(session, queryMetadata).from(paths);
    }

    public JPQLQuery createQuery(final Session session, final QueryMetadata queryMetadata) {
        return new HibernateQuery(new DefaultSessionHolder(session), HQLTemplates.DEFAULT, queryMetadata);
    }

    /**
     * STATELESS SESSION OPERATIONS
     */
    public JPQLQuery createQuery(final StatelessSession session) {
        return createQuery(session, new DefaultQueryMetadata());
    }

    public JPQLQuery createQuery(final StatelessSession session, final EntityPath<?>... paths) {
        return createQuery(session, new DefaultQueryMetadata(), paths);
    }

    public JPQLQuery createQuery(final StatelessSession session, final QueryMetadata queryMetadata, final EntityPath<?>... paths) {
        return createQuery(session, queryMetadata).from(paths);
    }

    public JPQLQuery createQuery(final StatelessSession session, final QueryMetadata queryMetadata) {
        return new HibernateQuery(new StatelessSessionHolder(session), HQLTemplates.DEFAULT, queryMetadata);
    }

    public Session getCurrentSession() {
        return (Session) em.getDelegate();
    }

    public Session openNewSession() {
        return getCurrentSession().getSessionFactory().openSession();
    }

    public StatelessSession openNewStatelessSession() {
        return getCurrentSession().getSessionFactory().openStatelessSession();
    }
}
