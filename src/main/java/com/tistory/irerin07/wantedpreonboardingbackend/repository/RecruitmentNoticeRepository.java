package com.tistory.irerin07.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl.RecruitmentNoticeRepositoryQueryDsl;

public interface RecruitmentNoticeRepository extends JpaRepository<RecruitmentNotice, Long>, JpaSpecificationExecutor<RecruitmentNotice>, RecruitmentNoticeRepositoryQueryDsl {
}
