package com.tistory.irerin07.wantedpreonboardingbackend.domain.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author 민경수
 * @description recruitment notice vo
 * @since 2023.10.07
 **********************************************************************************************************************/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecruitmentNoticeVo implements Serializable {

  private static final long serialVersionUID = 4354251634030258178L;


  @Getter
  @Setter
  @NoArgsConstructor
  @SuppressWarnings("java:S5843")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Create {
    /**
     * {
     *   "회사_id":회사_id,
     *   "채용포지션":"백엔드 주니어 개발자",
     *   "채용보상금":1000000,
     *   "채용내용":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
     *   "사용기술":"Python"
     * }
     */

    @NotNull(message = "회사 id를 입력해 주세요")
    private Long companySeq;

    @NotBlank(message = "반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.")
    private String jobPosition;

    @Min(value = 1, message = "채용 포상금은 0원 이상으로 입력해 주세요.")
    @NotNull(message = "채용 포상금을 입력해 주세요.")
    private Integer recruitReward;

    @NotBlank(message = "반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.")
    private String recruitDescription;

    @NotBlank(message = "반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.")
    private String requiredSkill;

    public RecruitmentNotice toEntity(Company company) {
      //@formatter:off
      return RecruitmentNotice.builder()
        .company(company)
        .recruitDescription(this.recruitDescription)
        .recruitReward(this.recruitReward)
        .jobPosition(this.jobPosition)
        .requiredSkill(this.requiredSkill)
        .build();
      //@formatter:on
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @SuppressWarnings("java:S5843")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Update {

    @NotNull(message = "회사 id를 입력해 주세요")
    private Long companySeq;

    @NotBlank(message = "반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.")
    private String jobPosition;

    @Min(value = 1, message = "채용 포상금은 0원 이상으로 입력해 주세요.")
    @NotNull(message = "채용 포상금을 입력해 주세요.")
    private Integer recruitReward;

    @NotBlank(message = "반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.")
    private String recruitDescription;

    @NotBlank(message = "반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.")
    private String requiredSkill;

    private Long seq;
  }

}
