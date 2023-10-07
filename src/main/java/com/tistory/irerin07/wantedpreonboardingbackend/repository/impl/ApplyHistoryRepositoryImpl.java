package com.tistory.irerin07.wantedpreonboardingbackend.repository.impl;

import static com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QApplyHistory.applyHistory;
import static com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QRecruitmentNotice.recruitmentNotice;
import static com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QUser.user;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
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

  @Override
  public boolean existsByUserSeqAndRecruitmentNoticeSeq(Long userSeq, Long recruitmentNoticeSeq) {
    // @formatter:off
    return findAll()
      .select(applyHistory.seq)
      .innerJoin(applyHistory.user, user).on(user.seq.eq(userSeq))
      .innerJoin(applyHistory.recruitmentNotice, recruitmentNotice).on(recruitmentNotice.seq.eq(recruitmentNoticeSeq), recruitmentNotice.deleteAt.isNull())
      .fetchFirst() != null;
    // @formatter:on
  }

  private JPQLQuery<ApplyHistory> findAll(Predicate... where) {
    return from(applyHistory).where(where);
  }

}
