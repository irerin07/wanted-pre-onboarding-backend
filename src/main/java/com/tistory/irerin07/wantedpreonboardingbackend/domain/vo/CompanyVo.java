package com.tistory.irerin07.wantedpreonboardingbackend.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.response.CompanyResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description company vo
 * @since 2023.10.07
 **********************************************************************************************************************/
public class CompanyVo {

  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
  public static class Response {

    private Long seq;
    private String name;
    private String country;
    private String region;

    @Builder
    public Response(Long seq, String name, String country, String region) {
      this.seq = seq;
      this.name = name;
      this.country = country;
      this.region = region;
    }

    public static Response toVo(CompanyResponse dto) {
      //@formatter:off
      return Response.builder()
        .seq(dto.getSeq())
        .name(dto.getCompanyName())
        .country(dto.getCountry())
        .region(dto.getRegion())
        .build();
      //@formatter:on
    }
  }
}
