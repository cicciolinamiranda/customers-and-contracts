package com.g4s.javelin.data.repository.common.support;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilder;

public abstract class AbstractJpqlQuerySupport extends AbstractQueryDslSupport {

    protected AbstractJpqlQuerySupport(final PathBuilder<?> builder) {
        super(builder);
    }

    public void applyPredicate(final JPQLQuery query, final Predicate predicate) {
        if (predicate != null) {
            query.where(predicate);
        }
    }

    public void applyOrders(final JPQLQuery query, final Iterable<OrderSpecifier<?>> orders) {
        if (orders != null) {
            for (OrderSpecifier<?> order : orders) {
                query.orderBy(order);
            }
        }
    }

    public void applyPagination(final JPQLQuery query, final Pageable pageable) {
        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());

            applySort(query, pageable.getSort());
        }
    }

    public void applySort(final JPQLQuery query, final Sort sort) {
        if (sort != null) {
            for (Sort.Order order : sort) {
                query.orderBy(toOrderSpecifier(order));
            }
        }
    }
}
