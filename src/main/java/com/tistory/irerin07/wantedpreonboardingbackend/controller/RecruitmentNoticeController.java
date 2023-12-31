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
import org.springframework.web.bind.annotation.RequestParam;
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

  //채용 공고 등록
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> create(@Validated @RequestBody RecruitmentNoticeVo.Create create) {
    service.set(create);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  // 채용 공고 조회
  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResourcesWrapper> read() {
    List<RecruitmentNoticeVo.Response> responses = service.getAll().stream().map(RecruitmentNoticeVo.Response::toVo).collect(Collectors.toList());

    return ResponseEntity.ok(new ResourcesWrapper.Builder(responses).build());
  }

  // 채용 상세 페이지 조회
  @GetMapping(path = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResourcesWrapper> read(@PathVariable("seq") Long seq) {
    return ResponseEntity.ok(new ResourcesWrapper.Builder(service.getResponse(seq)).build());
  }

  @GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResourcesWrapper> search(@RequestParam String keyword) {
    return ResponseEntity.ok(new ResourcesWrapper.Builder(service.get(keyword)).build());
  }

  // 채용 공고 수정
  @PutMapping(path = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> update(@PathVariable("seq") Long seq, @Validated @RequestBody RecruitmentNoticeVo.Update update) {
    service.modify(update, seq);

    return ResponseEntity.noContent().build();
  }

  // 채용 공고 삭제
  @DeleteMapping(path = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable("seq") Long seq) {
    service.remove(seq);

    return ResponseEntity.noContent().build();
  }

}
