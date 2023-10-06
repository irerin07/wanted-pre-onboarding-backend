package com.tistory.irerin07.wantedpreonboardingbackend.domain.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 민경수
 * @description company
 * @since 2023.10.06
 **********************************************************************************************************************/
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_company")
public class Company extends AbstractEntity{

  private static final long serialVersionUID = 7593219524018854620L;


  @ColumnDefault("")
  @Column(name = "company_name", nullable = false, length = 50)
  private String companyName;

  @ColumnDefault("")
  @Column(name = "country", nullable = false, length = 50)
  private String country;

  @ColumnDefault("")
  @Column(name = "region", nullable = false, length = 50)
  private String region;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.PERSIST)
  private Set<RecruitmentNotice> recruitmentNotices = new LinkedHashSet<>();

}
