package com.tistory.irerin07.wantedpreonboardingbackend.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 민경수
 * @description recruitment notice
 * @since 2023.10.06
 **********************************************************************************************************************/
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_recruitment_notice")
public class RecruitmentNotice extends AbstractEntity{

  private static final long serialVersionUID = 8212134289170406803L;


  @ColumnDefault("")
  @Column(name = "job_position", nullable = false, length = 50)
  private String jobPosition;

  @ColumnDefault("")
  @Column(name = "required_skill", nullable = false, length = 50)
  private String requiredSkill;

  @ColumnDefault("0")
  @Column(name = "recruit_reward", nullable = false)
  private Integer recruitReward;

  @ColumnDefault("")
  @Column(name = "recruit_description", nullable = false)
  private String recruitDescription;


  @CreatedDate
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  @Column(name = "create_at", nullable = false, updatable = false)
  private LocalDateTime createAt;

  @LastModifiedDate
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  @Column(name = "update_at")
  protected LocalDateTime updateAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  @Column(name = "delete_at", nullable = false, updatable = false)
  private LocalDateTime deleteAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "company_seq", nullable = false)
  private Company company;

}
