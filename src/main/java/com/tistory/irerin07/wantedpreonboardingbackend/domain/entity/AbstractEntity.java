package com.tistory.irerin07.wantedpreonboardingbackend.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author 민경수
 * @description abstract entity
 * @since 2023.10.06
 **********************************************************************************************************************/
@Getter
@EqualsAndHashCode(callSuper = false, of = {"seq"})
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  private static final long serialVersionUID = -5785158509172208133L;

  /**
   * 일련번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seq")
  protected Long seq;

}
