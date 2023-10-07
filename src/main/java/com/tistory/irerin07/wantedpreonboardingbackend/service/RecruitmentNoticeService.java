package com.tistory.irerin07.wantedpreonboardingbackend.service;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;

public interface RecruitmentNoticeService {
  void set(RecruitmentNoticeVo.Create create);

  void modify(RecruitmentNoticeVo.Update update, Long seq);

  void remove(Long seq);
}
