package com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;

@NoRepositoryBean
public interface RecruitmentNoticeRepositoryQueryDsl {

  Optional<RecruitmentNotice> findBySeq(Long seq);

}
