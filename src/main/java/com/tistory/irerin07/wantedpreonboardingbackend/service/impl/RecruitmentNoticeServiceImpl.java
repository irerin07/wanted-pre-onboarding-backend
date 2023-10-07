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
  public void set(RecruitmentNoticeVo.Create create) {
    repository.save(create.toEntity(companyService.get(create.getCompanySeq())));
  }

  @Transactional(readOnly = true)
  @Override
  public List<RecruitmentNoticeResponse> get() {
    return repository.findAllAvailable();
  }

  @Transactional(readOnly = true)
  @Override
  public RecruitmentNoticeVo.DetailResponse get(Long seq) {
    RecruitmentNoticeResponse recruitmentNoticeResponse = repository.findResponseBySeq(seq).orElseThrow(() -> new ResourceNotFoundException("채용 공고를 찾을 수 없습니다."));

    //@formatter:off
    List<Long> recruitmentNoticeSeqs = repository.findAllByCompanySeq(recruitmentNoticeResponse.getCompany().getSeq())
      .stream()
      .map(AbstractDto::getSeq)
      .filter(e -> !e.equals(seq))
      .collect(Collectors.toList());
    //@formatter:on

    return RecruitmentNoticeVo.DetailResponse.toVo(recruitmentNoticeResponse, recruitmentNoticeSeqs);
  }

  @Transactional
  @Override
  public void modify(RecruitmentNoticeVo.Update update, Long seq) {
    companyService.validateCompany(update.getCompanySeq());

    RecruitmentNotice recruitmentNotice = repository.findBySeq(seq).orElseThrow(() -> new ResourceNotFoundException("채용공고를 찾을 수 없습니다."));
    recruitmentNotice.modify(update.getRecruitDescription(), update.getRecruitReward(), update.getJobPosition(), update.getRequiredSkill());
  }

  @Transactional
  @Override
  public void remove(Long seq, Long companySeq) {
    repository.findBySeqAndCompanySeq(seq, companySeq).orElseThrow(() -> new ResourceNotFoundException("채용 공고를 찾을 수 없습니다.")).remove();
  }

}
