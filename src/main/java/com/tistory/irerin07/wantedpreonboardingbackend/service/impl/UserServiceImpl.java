package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.User;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.UserRepository;
import com.tistory.irerin07.wantedpreonboardingbackend.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * @author 민경수
 * @description user service impl
 * @since 2023.10.06
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public User get(Long seq) {
    return repository.findById(seq).orElseThrow(() -> new ResourceNotFoundException("유저를 찾을 수 없습니다."));
  }

}
