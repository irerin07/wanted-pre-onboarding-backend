package com.tistory.irerin07.wantedpreonboardingbackend.repository.impl;

import static com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QRecruitmentNotice.recruitmentNotice;

import java.util.Optional;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.irerin07.wantedpreonboardingbackend.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QRecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl.RecruitmentNoticeRepositoryQueryDsl;

/**
 * @author 민경수
 * @description recruitment notice repository impl
 * @since 2023.10.06
 **********************************************************************************************************************/
public class RecruitmentNoticeRepositoryImpl extends QueryDslRepositoryPaginationSupport implements RecruitmentNoticeRepositoryQueryDsl {

  public RecruitmentNoticeRepositoryImpl(JPAQueryFactory queryFactory) {
    super(RecruitmentNotice.class, queryFactory);
  }

  @Override
  public Optional<RecruitmentNotice> findBySeq(Long seq) {
    JPQLQuery<RecruitmentNotice> query = findOne(seq);

    return Optional.ofNullable(query.fetchOne());
  }

  private JPQLQuery<RecruitmentNotice> findOne(Long seq) {
    //@formatter:off
    return from(recruitmentNotice)
      .where(
        recruitmentNotice.seq.eq(seq),
        recruitmentNotice.deleteAt.isNull()
      );
    //@formatter:on
  }
}
