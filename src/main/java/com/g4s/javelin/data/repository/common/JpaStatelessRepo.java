package com.g4s.javelin.data.repository.common;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@NoRepositoryBean
public interface JpaStatelessRepo<T, ID extends Serializable> {

    long statelessCount();

    long statelessCount(Predicate predicate);

    boolean statelessExists(ID id);

    Iterable<T> statelessFindAll();

    Iterable<T> statelessFindAll(JpaQueryExpression query);

    Page<T> statelessFindAll(JpaQueryExpression query, Pageable pageable);

    Iterable<T> statelessFindAll(OrderSpecifier<?>... orders);

    Page<T> statelessFindAll(Pageable pageable);

    Iterable<T> statelessFindAll(Predicate predicate);

    Iterable<T> statelessFindAll(Predicate predicate,
            OrderSpecifier<?>... orders);

    Page<T> statelessFindAll(Predicate predicate, Pageable pageable);

    /**
     * MIRROR OF CrudRepository and PagingAndSortingRepository
     */
    T statelessFindOne(ID id);

    /**
     * MIRROR OF SelectExpressionJpaRepo
     */
    T statelessFindOne(JpaQueryExpression query);

    /**
     * MIRROR OF QueryDslPredicateExecutor
     */
    T statelessFindOne(Predicate predicate);
}
