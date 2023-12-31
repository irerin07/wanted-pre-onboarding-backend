package com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ApplyHistoryRepositoryQueryDsl {

  boolean existsByUserSeqAndRecruitmentNoticeSeq(Long userSeq, Long recruitmentNoticeSeq);

}
