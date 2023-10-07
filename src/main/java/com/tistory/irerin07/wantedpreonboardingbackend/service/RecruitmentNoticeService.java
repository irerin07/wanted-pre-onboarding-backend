package com.tistory.irerin07.wantedpreonboardingbackend.service;

import java.util.List;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;

public interface RecruitmentNoticeService {

  void set(RecruitmentNoticeVo.Create create);

  void modify(RecruitmentNoticeVo.Update update, Long seq);

  void remove(Long seq, Long companySeq);

  List<RecruitmentNoticeResponse> get();

  RecruitmentNoticeVo.DetailResponse get(Long seq);

}
