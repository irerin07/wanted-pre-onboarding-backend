package com.tistory.irerin07.wantedpreonboardingbackend.service;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;

public interface CompanyService {

  Company get(Long companySeq);

  void validateCompany(Long companySeq);

}
