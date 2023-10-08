package com.tistory.irerin07.wantedpreonboardingbackend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 민경수
 * @description invalid request exception
 * @since 2023.10.08
 **********************************************************************************************************************/
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidRequestException() {
    super("Abnormal data has been transferred. Please try again.");
  }

  public InvalidRequestException(String message) {
    super(message);
  }

}
