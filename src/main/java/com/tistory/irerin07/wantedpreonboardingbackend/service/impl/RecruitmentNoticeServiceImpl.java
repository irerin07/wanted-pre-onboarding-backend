package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
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
  public RecruitmentNotice get(Long seq) {
    return repository.findBySeq(seq).orElseThrow(() -> new ResourceNotFoundException("채용 공고를 찾을 수 없습니다."));
  }

  @Transactional(readOnly = true)
  @Override
  public List<RecruitmentNoticeResponse> getAll() {
    return repository.findAllAvailable();
  }

  @Transactional(readOnly = true)
  @Override
  public List<RecruitmentNoticeResponse> get(String keyword) {
    return repository.findAll(getSpecification(keyword)).stream().map(RecruitmentNoticeResponse::new).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public RecruitmentNoticeVo.DetailResponse getResponse(Long seq) {
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

  @Override
  public boolean existByRecruitmentNoticeSeq(Long seq) {
    return repository.existsBySeq(seq);
  }

  private Specification<RecruitmentNotice> getSpecification(String keyword) {
    return (root, criteriaQuery, criteriaBuilder) -> {
      criteriaQuery.distinct(true);

      root.join("company");

      //@formatter:off
      Predicate deleteAtPredicate = criteriaBuilder.and(criteriaBuilder.isNull(root.get("deleteAt")));

      Predicate keywordPredicate = criteriaBuilder.or(
        criteriaBuilder.like(root.get("jobPosition"), "%" + keyword + "%"),
        criteriaBuilder.like(root.get("recruitDescription"), "%" + keyword + "%"),
        criteriaBuilder.like(root.get("recruitReward").as(String.class), "%" + keyword + "%"),
        criteriaBuilder.like(root.get("requiredSkill"), "%" + keyword + "%"),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("company").get("companyName")), "%" + keyword + "%"),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("company").get("country")), "%" + keyword + "%"),
        criteriaBuilder.like(criteriaBuilder.lower(root.get("company").get("region")), "%" + keyword + "%")
        );
      //@formatter:on

      return criteriaBuilder.and(deleteAtPredicate, keywordPredicate);
    };
  }

}
