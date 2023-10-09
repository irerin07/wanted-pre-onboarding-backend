package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.dto.AbstractDto;
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
  public RecruitmentNotice set(RecruitmentNoticeVo.Create create) {
    return repository.save(create.toEntity(companyService.get(create.getCompanySeq())));
  }

  @Transactional(readOnly = true)
  @Override
  public RecruitmentNotice get(Long seq) {
    return repository.findBySeq(seq).orElseThrow(() -> new ResourceNotFoundException("채용 공고를 찾을 수 없습니다."));
  }

  @Transactional(readOnly = true)
  @Override
  public RecruitmentNoticeVo.DetailResponse getResponse(Long seq) {
    RecruitmentNoticeResponse recruitmentNoticeResponse = repository.findResponseBySeq(seq).orElseThrow(() -> new ResourceNotFoundException("채용 공고를 찾을 수 없습니다."));

    //@formatter:off
    List<Long> recruitmentNoticeSeqs = repository.findAllByCompanySeq(recruitmentNoticeResponse.getCompany().getSeq()).stream()
      .map(AbstractDto::getSeq)
      .filter(e -> !e.equals(seq))
      .collect(Collectors.toList());
    //@formatter:on

    return RecruitmentNoticeVo.DetailResponse.toVo(recruitmentNoticeResponse, recruitmentNoticeSeqs);
  }

  @Transactional(readOnly = true)
  @Override
  public List<RecruitmentNoticeResponse> getAll() {
    return repository.findAllAvailable();
  }

  @Transactional(readOnly = true)
  @Override
  public List<RecruitmentNoticeVo.Response> get(String keyword) {
    //@formatter:off
    return repository.findByKeyword(keyword).stream()
      .map(RecruitmentNoticeVo.Response::toVo)
      .collect(Collectors.toList());
    //@formatter:on
  }

  @Transactional
  @Override
  public RecruitmentNotice modify(RecruitmentNoticeVo.Update update, Long seq) {
    //@formatter:off
    return repository.findBySeq(seq)
      .orElseThrow(() -> new ResourceNotFoundException("채용공고를 찾을 수 없습니다."))
      .modify(update.getRecruitDescription(), update.getRecruitReward(), update.getJobPosition(), update.getRequiredSkill());
    //@formatter:on
  }

  @Transactional
  @Override
  public void remove(Long seq) {
    //@formatter:off
    repository.findBySeq(seq)
      .orElseThrow(() -> new ResourceNotFoundException("채용 공고를 찾을 수 없습니다."))
      .remove();
    //@formatter:on
  }

  @Override
  public boolean existByRecruitmentNoticeSeq(Long seq) {
    return repository.existsBySeq(seq);
  }

}
