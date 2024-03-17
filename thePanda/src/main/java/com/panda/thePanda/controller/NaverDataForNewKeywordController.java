package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.entity.keyword_info_base_name.KeywordInfoCustomByNewEntity;
import com.panda.thePanda.repository.keyword_info_base_name.KeywordInfoCustomByNewRepository;
import com.panda.thePanda.service.get_keyword.GetCategoryService;
import com.panda.thePanda.service.get_keyword.GetKeywordService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/keyword")
@RequiredArgsConstructor
public class NaverDataForNewKeywordController {
  private final GetKeywordService getKeywordService;
  private final GetCategoryService getCategoryService;
  private final KeywordInfoCustomByNewRepository keywordInfoCustomByNewRepository;

  @Operation(summary = "전체", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/00000000")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver00000000NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get00000000NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션의류", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000000")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000000NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get00000000NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션잡화", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000001")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000001NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get50000001NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "화장품/미용", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000002")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000002NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get50000002NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "디지털/가전", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000003")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000003NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get50000003NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "가구/인테리어", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000004")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000004NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get50000004NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "출산/육아", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000005")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000005RisingKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get50000005NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "식품", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000006")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000006NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get50000006NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "스포츠/레저", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000007")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000007NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get50000007NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "생활/건강", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000008")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000008NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get50000008NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "여가/생활편의", description = "새로운 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/new-keyword/50000009")
  public ResponseEntity<List<KeywordInfoCustomByNewEntity>> getNaver50000009NewKeyword() throws IOException {
    List<KeywordInfoCustomByNewEntity> keywordInfoList = keywordInfoCustomByNewRepository
        .get500000009NewKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

}
