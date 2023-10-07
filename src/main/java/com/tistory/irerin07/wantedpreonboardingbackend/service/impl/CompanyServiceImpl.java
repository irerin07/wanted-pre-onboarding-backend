package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.CompanyRepository;
import com.tistory.irerin07.wantedpreonboardingbackend.service.CompanyService;

import lombok.RequiredArgsConstructor;

/**
 * @author 민경수
 * @description company service impl
 * @since 2023.10.06
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository repository;

  @Transactional(readOnly = true)
  @Override
  public Company get(Long seq) {
    return repository.findById(seq).orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 회사 id입니다."));
  }

  @Transactional
  @Override
  public void validateCompany(Long seq) {
    if (!repository.existsById(seq)) {
      throw new ResourceNotFoundException("존재하지 않는 회사 id입니다.");
    }
  }

}
