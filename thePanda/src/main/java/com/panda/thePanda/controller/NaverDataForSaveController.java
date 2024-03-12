package com.panda.thePanda.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;
import com.panda.thePanda.service.keyword_name.KeywordNameService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/admin")
@RequiredArgsConstructor
public class NaverDataForSaveController {
  private final NaverSearchAPI naverSearchAPI;
  private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;
  private final KeywordNameService keywordNameService;

  @Operation(summary = "카테고리 별 500 키워드의 정보를 저장", description = "카테고리 별 키워드를 검색 후 데이터를 저장합니다.")
  @GetMapping("/naver-data/save")
  public Set<String> saveNaverDataCategory500(
      @RequestParam String category) throws IOException, GeneralSecurityException {

    return keywordNameService.saveKeyword(category);

  }
}
