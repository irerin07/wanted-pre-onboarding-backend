package com.tistory.irerin07.wantedpreonboardingbackend.service;

import java.util.List;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;

public interface RecruitmentNoticeService {

  void set(RecruitmentNoticeVo.Create create);

  RecruitmentNotice get(Long seq);

  List<RecruitmentNoticeResponse> getAll();

  RecruitmentNoticeVo.DetailResponse getResponse(Long seq);

  void modify(RecruitmentNoticeVo.Update update, Long seq);

  void remove(Long seq);

  boolean existByRecruitmentNoticeSeq(Long seq);

  List<RecruitmentNoticeVo.Response> get(String keyword);

}
