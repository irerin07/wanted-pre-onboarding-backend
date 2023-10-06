package com.tistory.irerin07.wantedpreonboardingbackend.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.irerin07.wantedpreonboardingbackend.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.User;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl.UserRepositoryQueryDsl;

/**
 * @author 민경수
 * @description user repository impl
 * @since 2023.10.06
 **********************************************************************************************************************/
public class UserRepositoryImpl extends QueryDslRepositoryPaginationSupport implements UserRepositoryQueryDsl {
  public UserRepositoryImpl(JPAQueryFactory queryFactory) {
    super(User.class, queryFactory);
  }
}
