package com.tistory.irerin07.wantedpreonboardingbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author 민경수
 * @description user controller
 * @since 2023.10.06
 **********************************************************************************************************************/
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = UserController.RESOURCE_URI)
public class UserController {

  public static final String RESOURCE_URI = "/users";


}
