package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.entity.keyword_info_base_name.KeywordInfoCustomByRisingEntity;
import com.panda.thePanda.repository.keyword_info_base_name.KeywordInfoCustomByRisingRepository;
import com.panda.thePanda.service.get_keyword.GetCategoryService;
import com.panda.thePanda.service.get_keyword.GetKeywordService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/keyword")
@RequiredArgsConstructor
public class NaverDataForRisingKeywordController {
  private final GetKeywordService getKeywordService;
  private final GetCategoryService getCategoryService;
  private final KeywordInfoCustomByRisingRepository keywordInfoCustomByRisingRepository;

  @Operation(summary = "전체", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/00000000")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver00000000RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get00000000RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션의류", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000000")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000000RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000000RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션잡화", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000001")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000001RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000001RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "화장품/미용", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000002")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000002RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000002RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "디지털/가전", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000003")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000003RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000003RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "가구/인테리어", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000004")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000004RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000004RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "출산/육아", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000005")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000005RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000005RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "식품", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000006")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000006RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000006RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "스포츠/레저", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000007")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000007RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000007RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "생활/건강", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000008")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000008RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000008RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "여가/생활편의", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000009")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000009RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000009RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

}
