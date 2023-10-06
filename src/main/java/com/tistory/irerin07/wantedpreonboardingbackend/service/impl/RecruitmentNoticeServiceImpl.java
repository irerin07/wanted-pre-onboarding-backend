package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.CompanyRepository;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.RecruitmentNoticeRepository;
import com.tistory.irerin07.wantedpreonboardingbackend.service.CompanyService;
import com.tistory.irerin07.wantedpreonboardingbackend.service.RecruitmentNoticeService;

import lombok.RequiredArgsConstructor;

/**
 * @author 민경수
 * @description recruitment notice repository
 * @since 2023.10.06
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class RecruitmentNoticeServiceImpl implements RecruitmentNoticeService {

  private final RecruitmentNoticeRepository repository;
  private final CompanyService companyService;

  @Transactional
  @Override
  public void set(RecruitmentNoticeVo.Create create) {
    repository.save(create.toEntity(companyService.get(create.getCompanySeq())));
  }

}
