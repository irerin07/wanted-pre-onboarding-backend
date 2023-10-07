package com.tistory.irerin07.wantedpreonboardingbackend.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.irerin07.wantedpreonboardingbackend.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl.CompanyRepositoryQueryDsl;

/**
 * @author 민경수
 * @description company repository impl
 * @since 2023.10.06
 **********************************************************************************************************************/
public class CompanyRepositoryImpl extends QueryDslRepositoryPaginationSupport implements CompanyRepositoryQueryDsl {
  public CompanyRepositoryImpl(JPAQueryFactory queryFactory) {
    super(Company.class, queryFactory);
  }
}
