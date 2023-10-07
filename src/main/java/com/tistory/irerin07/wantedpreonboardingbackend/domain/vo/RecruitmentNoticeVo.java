package com.tistory.irerin07.wantedpreonboardingbackend.domain.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.CompanyResponse;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.RecruitmentNoticeResponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
  public static class Response {
    private Long seq;
    private String jobPosition;
    private String requiredSkill;
    private Integer recruitReward;
    private String recruitDescription;

    private CompanyVo.Response company;

    @Builder
    public Response(Long seq, String jobPosition, String requiredSkill, Integer recruitReward, String recruitDescription, CompanyVo.Response company) {
      this.seq = seq;
      this.jobPosition = jobPosition;
      this.requiredSkill = requiredSkill;
      this.recruitReward = recruitReward;
      this.recruitDescription = recruitDescription;
      this.company = company;
    }

    public static Response toVo(RecruitmentNoticeResponse recruitmentNoticeResponse){
      return Response.builder()
        .seq(recruitmentNoticeResponse.getSeq())
        .jobPosition(recruitmentNoticeResponse.getJobPosition())
        .requiredSkill(recruitmentNoticeResponse.getRequiredSkill())
        .recruitReward(recruitmentNoticeResponse.getRecruitReward())
        .recruitDescription(recruitmentNoticeResponse.getRecruitDescription())
        .company(buildCompany(recruitmentNoticeResponse.getCompany()))
        .build();
    }

    static CompanyVo.Response buildCompany(CompanyResponse dto) {
      return null == dto ? null : CompanyVo.Response.toVo(dto);
    }

  }


}
