/*
 * Copyright (c) 2014-2015. KoolOkoy.
 * All rights reserved.
 */

package com.g4s.javelin.data.repository.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.mysema.query.types.FactoryExpression;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public interface QueryDslExtJpaRepo<T> extends QueryDslPredicateExecutor<T> {

    Iterable<T> findAll(FactoryExpression<T> factoryExpression,
            Predicate predicate);

    Iterable<T> findAll(FactoryExpression<T> factoryExpression,
            Predicate predicate, OrderSpecifier<?>... orderSpecifiers);

    Page<T> findAll(FactoryExpression<T> factoryExpression,
            Predicate predicate, Pageable pageable);
}
