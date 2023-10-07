package com.tistory.irerin07.wantedpreonboardingbackend.common.configuration.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * @author 민경수
 * @description jpa configuration
 * @since 2023.10.06
 **********************************************************************************************************************/
@Configuration
@EnableJpaAuditing
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class}, basePackages = {"com.tistory.irerin07.wantedpreonboardingbackend.domain.entity"})
@EnableJpaRepositories(basePackages = {"com.tistory.irerin07.wantedpreonboardingbackend.repository"})
public class JpaConfiguration {

  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }

}
