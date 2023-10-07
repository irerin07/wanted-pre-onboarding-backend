package com.tistory.irerin07.wantedpreonboardingbackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.irerin07.wantedpreonboardingbackend.autoconfigure.web.domain.ResourcesWrapper;
import com.tistory.irerin07.wantedpreonboardingbackend.domain.vo.RecruitmentNoticeVo;
import com.tistory.irerin07.wantedpreonboardingbackend.service.RecruitmentNoticeService;

import lombok.RequiredArgsConstructor;

/**
 * @author 민경수
 * @description recruitment notice controller
 * @since 2023.10.06
 **********************************************************************************************************************/
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = RecruitmentNoticeController.RESOURCE_URI)
public class RecruitmentNoticeController {

  public static final String RESOURCE_URI = "/recruitment-notices";

  private final RecruitmentNoticeService service;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> create(@Validated @RequestBody RecruitmentNoticeVo.Create create) {
    service.set(create);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  // TODO 응답 데이터에서 채용 내용 빼야함
  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResourcesWrapper> read() {
    List<RecruitmentNoticeVo.Response> responses = service.get().stream().map(RecruitmentNoticeVo.Response::toVo).collect(Collectors.toList());

    return ResponseEntity.ok(new ResourcesWrapper.Builder(responses).build());
  }

  @GetMapping(path = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResourcesWrapper> read(@PathVariable("seq") Long seq) {
    return ResponseEntity.ok(new ResourcesWrapper.Builder(RecruitmentNoticeVo.DetailResponse.toVo(service.get(seq))).build());
  }

  @PutMapping(path = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> update(@PathVariable("seq") Long seq, @Validated @RequestBody RecruitmentNoticeVo.Update update) {
    service.modify(update, seq);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{seq}/companies/{companySeq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable("seq") Long seq, @PathVariable("companySeq") Long companySeq) {
    service.remove(seq, companySeq);

    return ResponseEntity.noContent().build();
  }

}
