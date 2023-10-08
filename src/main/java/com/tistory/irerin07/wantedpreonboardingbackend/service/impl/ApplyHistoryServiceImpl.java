package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.irerin07.wantedpreonboardingbackend.common.exception.InvalidRequestException;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.ApplyHistory;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.ApplyHistoryVo;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.ApplyHistoryRepository;
import com.tistory.irerin07.wantedpreonboardingbackend.service.ApplyHistoryService;
import com.tistory.irerin07.wantedpreonboardingbackend.service.RecruitmentNoticeService;
import com.tistory.irerin07.wantedpreonboardingbackend.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * @author 민경수
 * @description apply history service impl
 * @since 2023.10.06
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class ApplyHistoryServiceImpl implements ApplyHistoryService {

  private final ApplyHistoryRepository repository;
  private final RecruitmentNoticeService recruitmentNoticeService;
  private final UserService userService;

  @Transactional
  @Override
  public void set(ApplyHistoryVo.Create create) {
    if (!recruitmentNoticeService.existByRecruitmentNoticeSeq(create.getRecruitmentNoticeSeq())) {
      throw new InvalidRequestException("지원할 수 없는 채용 공고입니다.");
    }

    if (repository.existsByUserSeqAndRecruitmentNoticeSeq(create.getUserSeq(), create.getRecruitmentNoticeSeq())) {
      throw new InvalidRequestException("이미 지원한 이력이 있는 채용 공고입니다.");
    }

    ApplyHistory applyHistory = create.toEntity(userService.get(create.getUserSeq()), recruitmentNoticeService.get(create.getRecruitmentNoticeSeq()));

    repository.save(applyHistory);
  }

}
