package com.tistory.irerin07.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl.CompanyRepositoryQueryDsl;

public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company>, CompanyRepositoryQueryDsl {
}
