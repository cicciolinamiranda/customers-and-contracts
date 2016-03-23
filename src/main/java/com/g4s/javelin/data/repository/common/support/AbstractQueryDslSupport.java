package com.g4s.javelin.data.repository.common.support;

import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import com.mysema.query.types.Expression;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;

public abstract class AbstractQueryDslSupport {

    protected final PathBuilder<?> builder;

    protected AbstractQueryDslSupport(final PathBuilder<?> builder) {
        Assert.notNull(builder);
        this.builder = builder;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected OrderSpecifier<?> toOrderSpecifier(final Sort.Order order) {

        Expression<Object> property = builder.get(order.getProperty());
        // Check that the specified order property is actually valid
        Validate.notNull(property, "Null querydsl expression detected when trying to convert string path: " + order.getProperty());

        return new OrderSpecifier(
                order.isAscending() ? Order.ASC : Order.DESC,
                property);
    }

}
