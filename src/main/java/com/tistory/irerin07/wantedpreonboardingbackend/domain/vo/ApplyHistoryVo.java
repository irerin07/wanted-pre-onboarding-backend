package com.tistory.irerin07.wantedpreonboardingbackend.domain.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.ApplyHistory;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description apply history vo
 * @since 2023.10.08
 **********************************************************************************************************************/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplyHistoryVo implements Serializable {

  private static final long serialVersionUID = 4067125828554146260L;


  @Getter
  @Setter
  @NoArgsConstructor
  @SuppressWarnings("java:S5843")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Create {

    @NotNull(message = "유저 id를 입력해 주세요.")
    private Long userSeq;

    @NotNull(message = "채용 공고 id를 입력해 주세요.")
    private Long recruitmentNoticeSeq;

    public ApplyHistory toEntity(User user, RecruitmentNotice recruitmentNotice) {
      //@formatter:off
      return ApplyHistory.builder()
        .user(user)
        .recruitmentNotice(recruitmentNotice)
        .build();
      //@formatter:on
    }

  }

}
