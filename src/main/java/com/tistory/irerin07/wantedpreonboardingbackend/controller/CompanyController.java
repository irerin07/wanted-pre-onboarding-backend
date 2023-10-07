package com.tistory.irerin07.wantedpreonboardingbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author 민경수
 * @description company controller
 * @since 2023.10.06
 **********************************************************************************************************************/
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = CompanyController.RESOURCE_URI)
public class CompanyController {

  public static final String RESOURCE_URI = "/companies";


}
