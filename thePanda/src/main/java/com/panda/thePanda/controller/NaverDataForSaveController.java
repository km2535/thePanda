package com.panda.thePanda.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.dto.ListKeywordAndCategoryDTO;
import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;
import com.panda.thePanda.service.data_collection.DataCollectionFromBrowser;
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
  private final DataCollectionFromBrowser dataCollectionFromBrowser;

  @Operation(summary = "카테고리 별 500 키워드의 정보를 저장", description = "카테고리 별 키워드를 검색 후 데이터를 저장합니다.")
  @GetMapping("/naver-data/save")
  public Set<String> saveNaverDataCategory500(
      @RequestParam String category) throws IOException, GeneralSecurityException, UnirestException {
    return keywordNameService.saveKeyword(category);
  }

  @Operation(summary = "데이터를 백업합니다.", description = "데이터 백업")
  @GetMapping("/data/backup")
  public String backupData(
      @RequestParam String category) throws IOException, GeneralSecurityException {
    return dataCollectionFromBrowser.backupData(category);
  }

  @Operation(summary = "데이터랩에서 키워드 가져오기", description = "데이터랩에서 키워드를 가져옵니다.")
  @GetMapping("/datalab/get-keyword")
  public List<String> getKeywordByDataLab(
      @RequestParam String category) throws IOException, GeneralSecurityException {
    return dataCollectionFromBrowser.saveDatalab(category);
  }

  @Operation(summary = "상품의 카테고리를 가져옵니다.", description = "상품의 카테고리 검색 API")
  @PostMapping("/get-data/product-category")
  public List<String> getProductCategories(
      ListKeywordAndCategoryDTO requestDto)
      throws IOException, GeneralSecurityException, UnirestException {
    return dataCollectionFromBrowser.saveCategory(requestDto);
  }

  @Operation(summary = "상품의 계절성 여부를 가져옵니다.", description = "계절 상품 검색 API")
  @PostMapping("/get-data/product-season")
  public List<String> getIsSeasonProduct(
      ListKeywordAndCategoryDTO requestDto)
      throws IOException, GeneralSecurityException, UnirestException {
    return dataCollectionFromBrowser.savaIsSeason(requestDto);
  }

  @Operation(summary = "키워드 클릭률을 가져옵니다", description = "검색 수, 클릭률 반환")
  @PostMapping("/get-data/product-click")
  public Set<String> getClickCountProduct(
      ListKeywordAndCategoryDTO requestDto)
      throws IOException, GeneralSecurityException {
    return dataCollectionFromBrowser.saveKeywordToCmp(requestDto);
  }

  @Operation(summary = "에러 키워드를 저장합니다.", description = "에러키워드 리스트형태로 저장")
  @PostMapping("/save-error/product")
  public void getErrorList(
      ListKeywordAndCategoryDTO requestDto)
      throws IOException, GeneralSecurityException {
    dataCollectionFromBrowser.saveErrorKeyword(requestDto);
  }

}
