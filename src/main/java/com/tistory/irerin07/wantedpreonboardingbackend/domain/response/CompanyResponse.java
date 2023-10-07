package com.tistory.irerin07.wantedpreonboardingbackend.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.dto.CompanyDto;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description company response
 * @since 2023.10.07
 **********************************************************************************************************************/
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(value = {"createdBy", "updatedBy", "updateAt", "phoneNo", "user"}, ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class CompanyResponse extends CompanyDto {

  private static final long serialVersionUID = -7907064066576446603L;


  @Builder
  public CompanyResponse(Long seq, String companyName, String country, String region) {
    super(seq, companyName, country, region);
  }

  @QueryProjection
  public CompanyResponse(Company company) {
    this(company.getSeq(), company.getCompanyName(), company.getCountry(), company.getRegion());
  }

}
