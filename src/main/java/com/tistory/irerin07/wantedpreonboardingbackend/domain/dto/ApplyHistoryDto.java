package com.tistory.irerin07.wantedpreonboardingbackend.domain.dto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description apply history dto
 * @since 2023.10.06
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyHistoryDto extends AbstractDto{

  private static final long serialVersionUID = -8910204286609168398L;


  public ApplyHistoryDto(Long seq) {
    super(seq);
  }

}
