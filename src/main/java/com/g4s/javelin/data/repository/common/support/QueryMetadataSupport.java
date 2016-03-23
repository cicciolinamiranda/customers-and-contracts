package com.g4s.javelin.data.repository.common.support;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mysema.query.DefaultQueryMetadata;
import com.mysema.query.JoinExpression;
import com.mysema.query.JoinType;
import com.mysema.query.QueryMetadata;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;
public class QueryMetadataSupport extends AbstractQueryDslSupport {


    public QueryMetadataSupport(final PathBuilder<?> builder) {
        super(builder);
    }

    public QueryMetadata createQueryMetadata(final EntityPath<?> root) {
        QueryMetadata queryMetadata = new DefaultQueryMetadata();

        // Create root path
        queryMetadata.addJoin(JoinType.DEFAULT, root);

        return queryMetadata;
    }

    public void applyJoins(final QueryMetadata queryMetadata, final Iterable<JoinExpression> joins) {
        if (joins != null) {
            for (JoinExpression join : joins) {
                queryMetadata.addJoin(join.getType(), join.getTarget());
            }
        }
    }

    public void applyOrders(final QueryMetadata queryMetadata, final Iterable<OrderSpecifier<?>> orders) {
        if (orders != null) {
            for (OrderSpecifier<?> order : orders) {
                queryMetadata.addOrderBy(order);
            }
        }
    }

    public void applyPagination(final QueryMetadata queryMetadata, final Pageable pageable) {
        if (pageable != null) {
            queryMetadata.setOffset((long) pageable.getOffset());
            queryMetadata.setLimit((long) pageable.getPageSize());

            applySort(queryMetadata, pageable.getSort());
        }
    }

    public void applySort(final QueryMetadata queryMetadata, final Sort sort) {
        if (sort != null) {
            for (Sort.Order order : sort) {
                queryMetadata.addOrderBy(toOrderSpecifier(order));
            }
        }
    }

}
