/*
 * Copyright (c) 2014-2015. KoolOkoy.
 * All rights reserved.
 */

package com.g4s.javelin.data.repository.common;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaQueryExpressionRepo<T, ID extends Serializable> {

    Iterable<T> findAll(JpaQueryExpression query);

    Page<T> findAll(JpaQueryExpression query, Pageable pageable);

    T findOne(JpaQueryExpression query);

}
