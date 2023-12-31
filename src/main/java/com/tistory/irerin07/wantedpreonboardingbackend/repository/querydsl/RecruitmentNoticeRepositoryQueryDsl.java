package com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;

@NoRepositoryBean
public interface RecruitmentNoticeRepositoryQueryDsl {

  Optional<RecruitmentNotice> findBySeq(Long seq);

  Optional<RecruitmentNoticeResponse> findResponseBySeq(Long seq);

  List<RecruitmentNoticeResponse> findAllAvailable();

  List<RecruitmentNoticeResponse> findAllByCompanySeq(Long companySeq);

  List<RecruitmentNoticeResponse> findByKeyword(String keyword);

  boolean existsBySeq(Long seq);

}
