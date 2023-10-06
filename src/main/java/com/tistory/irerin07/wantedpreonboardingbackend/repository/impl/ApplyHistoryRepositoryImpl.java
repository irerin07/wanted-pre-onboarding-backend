package com.tistory.irerin07.wantedpreonboardingbackend.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.irerin07.wantedpreonboardingbackend.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.ApplyHistory;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl.ApplyHistoryRepositoryQueryDsl;

/**
 * @author 민경수
 * @description apply history repository impl
 * @since 2023.10.06
 **********************************************************************************************************************/
public class ApplyHistoryRepositoryImpl extends QueryDslRepositoryPaginationSupport implements ApplyHistoryRepositoryQueryDsl {
  public ApplyHistoryRepositoryImpl(JPAQueryFactory queryFactory) {
    super(ApplyHistory.class, queryFactory);
  }
}
