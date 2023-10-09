package com.tistory.irerin07.wantedpreonboardingbackend.service;

import java.util.List;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;

public interface RecruitmentNoticeService {

  RecruitmentNotice set(RecruitmentNoticeVo.Create create);

  RecruitmentNotice get(Long seq);

  RecruitmentNoticeVo.DetailResponse getResponse(Long seq);

  List<RecruitmentNoticeResponse> getAll();

  List<RecruitmentNoticeVo.Response> get(String keyword);

  RecruitmentNotice modify(RecruitmentNoticeVo.Update update, Long seq);

  void remove(Long seq);

  boolean existByRecruitmentNoticeSeq(Long seq);

}
