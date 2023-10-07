package com.tistory.irerin07.wantedpreonboardingbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;

public interface RecruitmentNoticeService {
  void set(RecruitmentNoticeVo.Create create);

  void modify(RecruitmentNoticeVo.Update update, Long seq);

  void remove(Long seq);

  Page<RecruitmentNoticeResponse> get(Pageable pageable);

  RecruitmentNoticeResponse get(Long seq);

}
