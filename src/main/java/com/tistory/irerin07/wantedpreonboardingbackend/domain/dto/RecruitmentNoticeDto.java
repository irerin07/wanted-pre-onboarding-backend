package com.tistory.irerin07.wantedpreonboardingbackend.domain.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description recruitment notice dto
 * @since 2023.10.06
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentNoticeDto extends AbstractDto{
  private static final long serialVersionUID = -2435222968381901272L;


  private String jobPosition;

  private String requiredSkill;

  private Integer recruitReward;

  private String recruitDescription;

  private LocalDateTime createAt;

  protected LocalDateTime updateAt;

  private LocalDateTime deleteAt;

  public RecruitmentNoticeDto(Long seq, String jobPosition, String requiredSkill, Integer recruitReward, String recruitDescription, LocalDateTime createAt, LocalDateTime updateAt,
    LocalDateTime deleteAt) {
    super(seq);
    this.jobPosition = jobPosition;
    this.requiredSkill = requiredSkill;
    this.recruitReward = recruitReward;
    this.recruitDescription = recruitDescription;
    this.createAt = createAt;
    this.updateAt = updateAt;
    this.deleteAt = deleteAt;
  }
}
