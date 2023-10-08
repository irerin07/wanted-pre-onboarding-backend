package com.tistory.irerin07.wantedpreonboardingbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.ApplyHistoryVo;
import com.tistory.irerin07.wantedpreonboardingbackend.service.ApplyHistoryService;

import lombok.RequiredArgsConstructor;

/**
 * @author 민경수
 * @description apply history controller
 * @since 2023.10.06
 **********************************************************************************************************************/
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApplyController.RESOURCE_URI)
public class ApplyController {

  public static final String RESOURCE_URI = "/apply";

  private final ApplyHistoryService service;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> create(@Validated @RequestBody ApplyHistoryVo.Create create) {
    service.set(create);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


}
