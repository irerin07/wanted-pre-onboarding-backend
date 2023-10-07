package com.tistory.irerin07.wantedpreonboardingbackend.service;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;

/**
 * @author 민경수
 * @description company service
 * @since 2023.10.06
 **********************************************************************************************************************/
public interface CompanyService {
  Company get(Long companySeq);

  void validateCompany(Long companySeq);

}
