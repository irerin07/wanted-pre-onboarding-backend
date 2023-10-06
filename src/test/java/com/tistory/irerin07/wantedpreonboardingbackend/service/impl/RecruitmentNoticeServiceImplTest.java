package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.RecruitmentNoticeRepository;
import com.tistory.irerin07.wantedpreonboardingbackend.service.RecruitmentNoticeService;

@ExtendWith(MockitoExtension.class)
class RecruitmentNoticeServiceImplTest {

  @Mock
  private RecruitmentNoticeRepository repository;

  @InjectMocks
  private RecruitmentNoticeServiceImpl service;

  @Test
  @DisplayName("채용공고 등록시 필요 데이터 누락")
  void missingData() {

  }

  @Test
  @DisplayName("채용공고 등록")
  void createRecruitmentNotice() {
    Company company = Company.builder().companyName("test company").country("한국").region("서울").build();
    RecruitmentNotice recruitmentNotice = RecruitmentNotice.builder().recruitReward(1000000).recruitDescription("채용 내용").company(company).jobPosition("백엔드 개발자").requiredSkill("Java").build();

    RecruitmentNotice save = repository.save(recruitmentNotice);


  }


}
