package com.tistory.irerin07.wantedpreonboardingbackend.domain.dto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description user dto
 * @since 2023.10.06
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto extends AbstractDto{

  private static final long serialVersionUID = -5900446930989885993L;


  private String name;

  public UserDto(Long seq, String name) {
    super(seq);
    this.name = name;
  }

}
