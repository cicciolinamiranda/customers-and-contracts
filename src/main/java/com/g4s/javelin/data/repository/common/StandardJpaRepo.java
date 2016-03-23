package com.g4s.javelin.data.repository.common;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface StandardJpaRepo<T, ID extends Serializable> extends
        JpaQueryExpressionRepo<T, ID>, JpaStatelessRepo<T, ID>,
        PagingAndSortingRepository<T, ID>, QueryDslExtJpaRepo<T> {

}
