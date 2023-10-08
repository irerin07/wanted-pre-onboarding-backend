package com.tistory.irerin07.wantedpreonboardingbackend.repository.impl;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QCompany.company;
import static com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.QRecruitmentNotice.recruitmentNotice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.irerin07.wantedpreonboardingbackend.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
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
    JPQLQuery<RecruitmentNotice> query = findOne(recruitmentNotice.seq.eq(seq), recruitmentNotice.deleteAt.isNull());

    return Optional.ofNullable(query.fetchOne());
  }

  @Override
  public Optional<RecruitmentNoticeResponse> findResponseBySeq(Long seq) {
    JPQLQuery<RecruitmentNotice> query = this.findOne(recruitmentNotice.seq.eq(seq), recruitmentNotice.deleteAt.isNull());

    List<RecruitmentNoticeResponse> responses = query.transform(groupBy(recruitmentNotice).list(getExpression()));

    return responses.isEmpty() ? Optional.empty() : Optional.of(responses.get(0));
  }

  @Override
  public List<RecruitmentNoticeResponse> findAllAvailable() {
    //@formatter:off
    JPQLQuery<RecruitmentNotice> query = findAll(recruitmentNotice.deleteAt.isNull())
      .innerJoin(recruitmentNotice.company, company)
      .orderBy(recruitmentNotice.seq.desc());
    //@formatter:on
    return query.transform(groupBy(recruitmentNotice).list(getExpression()));
  }

  @Override
  public Optional<RecruitmentNotice> findBySeqAndCompanySeq(Long seq, Long companySeq) {
    //@formatter:off
    JPQLQuery<RecruitmentNotice> query = findOne(recruitmentNotice.seq.eq(seq),recruitmentNotice.deleteAt.isNull(), company.seq.eq(companySeq))
      .innerJoin(recruitmentNotice.company, company);
    //@formatter:on

    return Optional.ofNullable(query.fetchOne());
  }

  @Override
  public List<RecruitmentNoticeResponse> findAllByCompanySeq(Long companySeq) {
    JPQLQuery<RecruitmentNotice> query = findAll(recruitmentNotice.company.seq.eq(companySeq), recruitmentNotice.deleteAt.isNull()).orderBy(recruitmentNotice.seq.desc());


    return query.transform(groupBy(recruitmentNotice).list(getExpression()));
  }

  @Override
  public List<RecruitmentNoticeResponse> findAllBySpecification(Specification<RecruitmentNotice> specification) {
    return null;
  }

  @Override
  public boolean existsBySeq(Long seq) {
    return findAll(recruitmentNotice.seq.eq(seq), recruitmentNotice.deleteAt.isNull()).select(recruitmentNotice.seq).fetchFirst() != null;
  }

  private JPQLQuery<RecruitmentNotice> findOne(Predicate... where) {
    //@formatter:off
    return from(recruitmentNotice)
      .where(where);
    //@formatter:on
  }

  private JPQLQuery<RecruitmentNotice> findAll(Predicate... where) {
    //@formatter:off
    return from(recruitmentNotice)
      .where(where);
    //@formatter:on
  }

  private Page<Long> buildIdsPage(Pageable pageable, JPQLQuery<Long> idsQuery) {
    return applyPagination(pageable, idsQuery);
  }

  public FactoryExpression<RecruitmentNoticeResponse> getExpression() {
    return new QRecruitmentNoticeResponse(recruitmentNotice, new QCompanyResponse(company).skipNulls()).skipNulls();
  }

}
