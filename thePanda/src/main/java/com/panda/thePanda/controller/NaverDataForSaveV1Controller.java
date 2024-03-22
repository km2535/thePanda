package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;
import com.panda.thePanda.service.keyword_save.KeywordSaveService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/panda-v1/amin/naver")
public class NaverDataForSaveV1Controller {
  private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;
  private final KeywordSaveService keywordSaveService;

  @Operation(summary = "인기 검색어 받기 (top 500 키워드)", description = "모든 카테고리를 받아 db에 저장합니다.")
  @GetMapping("/save/top-keyword")
  public List<String> saveEveryCategoryProduct(@RequestParam String category) throws IOException {
    return keywordSaveService.searchAndSave(category);
  }

  @Operation(summary = "500키워드 테이블 삭제", description = "테이블의 모든 데이터 삭제.")
  @GetMapping("/delete/top-keyword")
  public void deleteEveryCategoryProduct(@RequestParam String category) throws IOException {
    keywordSaveService.deleteAllData();
  }

}
