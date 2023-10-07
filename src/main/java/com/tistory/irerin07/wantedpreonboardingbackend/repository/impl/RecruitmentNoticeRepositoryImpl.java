package com.tistory.irerin07.wantedpreonboardingbackend.repository.impl;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QCompany.company;
import static com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QRecruitmentNotice.recruitmentNotice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.irerin07.wantedpreonboardingbackend.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QCompany;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QRecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.QCompanyResponse;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.QRecruitmentNoticeResponse;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;
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

  @Override
  public Page<RecruitmentNoticeResponse> findAllAvailable(Pageable pageable) {
    JPQLQuery<Long> query = findAll()
      .select(recruitmentNotice.seq);

    Page<Long> page = buildIdsPage(pageable, query);

    return new PageImpl<>(
      findAll(recruitmentNotice.seq.in(page.getContent()))
        .orderBy(recruitmentNotice.seq.desc())
        .transform(groupBy(recruitmentNotice).list(getExpression())),
      page.getPageable(),
      page.getTotalElements()
    );
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

  private JPQLQuery<RecruitmentNotice> findAll() {
    //@formatter:off
    return from(recruitmentNotice)
      .where(
        recruitmentNotice.deleteAt.isNull()
      );
    //@formatter:on
  }

  private JPQLQuery<RecruitmentNotice> findAll(Predicate... where) {
    // @formatter:off
    JPQLQuery<RecruitmentNotice> query = from(recruitmentNotice)
      .innerJoin(recruitmentNotice.company, company);
    // @formatter:on

    return query.where(where);
  }

  private Page<Long> buildIdsPage(Pageable pageable, JPQLQuery<Long> idsQuery) {
    return applyPagination(pageable, idsQuery);
  }

  public FactoryExpression<RecruitmentNoticeResponse> getExpression() {
    //@formatter:off
    return new QRecruitmentNoticeResponse(recruitmentNotice, new QCompanyResponse(company).skipNulls()).skipNulls();
    //@formatter:on
  }

}
