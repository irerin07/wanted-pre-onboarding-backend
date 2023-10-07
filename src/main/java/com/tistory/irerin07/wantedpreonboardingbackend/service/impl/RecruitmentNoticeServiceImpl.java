package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;
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

  @Transactional(readOnly = true)
  @Override
  public Page<RecruitmentNoticeResponse> get(Pageable pageable) {
    return repository.findAllAvailable(pageable);
  }

  @Transactional(readOnly = true)
  @Override
  public RecruitmentNoticeResponse get(Long seq) {
    return repository.findResponseBySeq(seq).orElseThrow(() -> new ResourceNotFoundException("채용 공고를 찾을 수 없습니다."));
  }

  @Transactional
  @Override
  public void modify(RecruitmentNoticeVo.Update update, Long seq) {
    companyService.validateCompany(update.getCompanySeq());

    // TODO 채용공고 조회시 조건에 회사 id도 함께 걸어서 조회 하도록 수정
    RecruitmentNotice recruitmentNotice = repository.findBySeq(seq).orElseThrow(() -> new ResourceNotFoundException("채용공고를 찾을 수 없습니다."));
    recruitmentNotice.modify(update.getRecruitDescription(), update.getRecruitReward(), update.getJobPosition(), update.getRequiredSkill());
  }

  // TODO 회사 id도 함께 받도록 수정
  @Transactional
  @Override
  public void remove(Long seq) {
    // TODO 채용공고 조회시 조건에 회사 id도 함께 걸어서 조회 하도록 수정
    repository.findBySeq(seq).orElseThrow(() -> new ResourceNotFoundException("채용 공고를 찾을 수 없습니다.")).remove();
  }

}
