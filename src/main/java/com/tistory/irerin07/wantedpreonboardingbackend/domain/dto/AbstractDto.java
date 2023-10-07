package com.tistory.irerin07.wantedpreonboardingbackend.domain.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description abstrct dto
 * @since 2023.10.06
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"seq"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class AbstractDto implements Serializable {

  private static final long serialVersionUID = -15859046453442429L;

  /**
   * 일련번호
   */
  private Long seq;

}
