package com.tistory.irerin07.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.User;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.querydsl.UserRepositoryQueryDsl;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, UserRepositoryQueryDsl {
}
