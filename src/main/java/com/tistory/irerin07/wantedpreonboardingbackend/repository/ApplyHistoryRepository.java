package com.tistory.irerin07.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.ApplyHistory;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl.ApplyHistoryRepositoryQueryDsl;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Long>, JpaSpecificationExecutor<ApplyHistory>, ApplyHistoryRepositoryQueryDsl {
}
