/*
 * Copyright (c) 2014-2015. KoolOkoy.
 * All rights reserved.
 */
package com.g4s.javelin.data.repository.common;

import java.util.List;

import com.mysema.query.JoinExpression;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public interface JpaQueryExpression {

    List<JoinExpression> getJoins();

    List<OrderSpecifier<?>> getOrders();

    Predicate getWhere();

    boolean isDistinct();

}
