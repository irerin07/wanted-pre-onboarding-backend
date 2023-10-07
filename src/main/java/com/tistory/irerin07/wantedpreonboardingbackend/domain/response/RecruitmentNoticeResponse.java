package com.tistory.irerin07.wantedpreonboardingbackend.domain.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.dto.RecruitmentNoticeDto;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description recruitment notice response
 * @since 2023.10.07
 **********************************************************************************************************************/
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(value = {"createdBy", "updatedBy", "updateAt", "phoneNo", "user"}, ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class RecruitmentNoticeResponse extends RecruitmentNoticeDto {

  private static final long serialVersionUID = 4448117875004970983L;

  private CompanyResponse company;


  @Builder
  public RecruitmentNoticeResponse(Long seq, String jobPosition, String requiredSkill, Integer recruitReward, String recruitDescription, LocalDateTime createAt, LocalDateTime updateAt,
    LocalDateTime deleteAt) {
    super(seq, jobPosition, requiredSkill, recruitReward, recruitDescription, createAt, updateAt, deleteAt);
  }

  @QueryProjection
  public RecruitmentNoticeResponse(RecruitmentNotice recruitmentNotice) {
    this(recruitmentNotice.getSeq(), recruitmentNotice.getJobPosition(), recruitmentNotice.getRequiredSkill(), recruitmentNotice.getRecruitReward(), recruitmentNotice.getRecruitDescription(),
      recruitmentNotice.getCreateAt(), recruitmentNotice.getUpdateAt(), recruitmentNotice.getDeleteAt());
  }

  @QueryProjection
  public RecruitmentNoticeResponse(RecruitmentNotice recruitmentNotice, CompanyResponse company) {
    this(recruitmentNotice);
    this.company = company;

  }
}
