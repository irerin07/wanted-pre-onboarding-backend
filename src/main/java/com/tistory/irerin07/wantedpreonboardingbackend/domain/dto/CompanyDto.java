package com.tistory.irerin07.wantedpreonboardingbackend.domain.dto;

import javax.persistence.Column;

import org.hibernate.annotations.ColumnDefault;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description company dto
 * @since 2023.10.06
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyDto extends AbstractDto{
  private static final long serialVersionUID = 1342872351731263292L;


  private String companyName;

  private String country;

  private String region;

  public CompanyDto(Long seq, String companyName, String country, String region) {
    super(seq);
    this.companyName = companyName;
    this.country = country;
    this.region = region;
  }

}
