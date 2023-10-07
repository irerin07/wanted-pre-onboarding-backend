package com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;

@NoRepositoryBean
public interface RecruitmentNoticeRepositoryQueryDsl {

  Optional<RecruitmentNotice> findBySeq(Long seq);

  Page<RecruitmentNoticeResponse> findAllAvailable(Pageable pageable);

}
