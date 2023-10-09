package com.tistory.irerin07.wantedpreonboardingbackend.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.Company;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.entity.RecruitmentNotice;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;
import com.tistory.irerin07.wantedpreonboardingbackend.repository.RecruitmentNoticeRepository;
import com.tistory.irerin07.wantedpreonboardingbackend.service.CompanyService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class RecruitmentNoticeServiceImplTest {

  @MockBean
  private RecruitmentNoticeRepository repository;

  @MockBean
  private CompanyService companyService;

  @SpyBean
  private RecruitmentNoticeServiceImpl service;

  private final Company mockCompany = mock(Company.class);

  private final RecruitmentNotice recruitmentNotice =
    RecruitmentNotice.builder().recruitDescription("채용 내용").requiredSkill("Java").recruitReward(120000).jobPosition("백엔드").company(mockCompany).build();

  @BeforeEach
  public void init() {
    doReturn(1L).when(mockCompany).getSeq();
    doReturn("test company").when(mockCompany).getCompanyName();
    doReturn("대한민국").when(mockCompany).getCountry();
    doReturn("서울").when(mockCompany).getRegion();

  }

  @Test
  public void set_recruitment_notice() throws Exception {
    doReturn(mockCompany).when(this.companyService).get(anyLong());

    doReturn(RecruitmentNotice.builder().build()).when(this.repository).save(any());

    RecruitmentNoticeVo.Create create = RecruitmentNoticeVo.Create.builder().recruitDescription("채용내용").recruitReward(120000).companySeq(1L).jobPosition("백엔드").requiredSkill("java").build();

    RecruitmentNotice notice = service.set(create);


    assertThat(notice).isNotNull();
  }

  @Test
  public void modify_recruitment_notice() throws Exception {
    doReturn(Optional.of(recruitmentNotice)).when(repository).findBySeq(anyLong());

    RecruitmentNoticeVo.Update update = RecruitmentNoticeVo.Update.builder().seq(1L).recruitDescription("채용내용").recruitReward(5000).jobPosition("백엔드").requiredSkill("java").build();

    RecruitmentNotice modify = service.modify(update, 1L);

    assertThat(modify.getRecruitReward()).isNotEqualTo(recruitmentNotice.getRecruitReward());
  }

  @Test
  public void delete_recruitment_notice() throws Exception {

  }

}
