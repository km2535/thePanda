package com.panda.thePanda.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.dto.ListKeywordAndCategoryDTO;
import com.panda.thePanda.entity.keyword_save.KeywordDetailEntity;
import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;
import com.panda.thePanda.service.keyword_detail.KeywordDetailGetDataService;
import com.panda.thePanda.service.keyword_detail.KeywordDetailSaveAndDeleteService;
import com.panda.thePanda.service.keyword_save.KeywordSaveService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/panda-v1/amin/naver")
public class NaverDataForSaveV1Controller {
  private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;
  private final KeywordSaveService keywordSaveService;
  private final KeywordDetailSaveAndDeleteService saveAndDeleteService;
  private final KeywordDetailGetDataService detailGetDataService;
  private final NaverSearchAPI searchAPI;

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

  @Operation(summary = "키워드 결과 삭제", description = "테이블의 모든 데이터 삭제.")
  @GetMapping("/delete/detail-keyword")
  public void deleteEveryDetailProduct(@RequestParam String category) throws IOException {
    saveAndDeleteService.deleteAllDetailData();
  }

  @Operation(summary = "키워드 클릭률을 저장합니다.", description = "검색 수, 클릭률 반환")
  @PostMapping("/save-data/product-click")
  public Set<String> getClickCountProduct(@RequestBody ListKeywordAndCategoryDTO requestDto)
      throws IOException, GeneralSecurityException, UnirestException {
    // 500 키워드를 저장하고 바로 이어서 요청해야 합니다.
    return saveAndDeleteService.saveRltKeyword(requestDto);
  }

  @Operation(summary = "키워드 카테고리와 상품 순위를 저장합니다.", description = "키워드별 상품 순위와 1순위 카테고리를 저장합니다.")
  @PostMapping("/save-data/product-category")
  public String getProductCategory() throws IOException, GeneralSecurityException, UnirestException {
    // detail 테이블을 가져와서 카테고리를 갱신하고 20위 상품 데이터를 별도로 저장합니다.
    saveAndDeleteService.saveCategoryProduct();
    return "OK";
  }

  // 계절성 상품을 갱신하는 함수
  @Operation(summary = "계절성 키워드 여부 확인.", description = "계절성 키워드 여부를 확인합니다.")
  @PostMapping("/save-data/product-season")
  public List<String> saveProductIsSeason() throws IOException, GeneralSecurityException, UnirestException {
    return saveAndDeleteService.save_is_season();
  }

  // 광고비용 갱신하는 함수
  @Operation(summary = "광고비용 갱신", description = "광고 비용을 갱신합니다.")
  @PostMapping("/save-data/product-adcost")
  public List<String> saveProductAdCost() throws IOException, GeneralSecurityException, UnirestException {
    // TODO: 광고비용 API를 요청하고 해당 데이터를 저장한다.

    return null;
  }

  @Operation(summary = "500키워드의 detail 테이블 내용을 백업", description = "500키워드의 detail 테이블 내용을 백업")
  @PostMapping("/back-up/product-category")
  public String backupProductCategory() throws IOException, GeneralSecurityException, UnirestException {
    saveAndDeleteService.backup_data();
    return "OK";
  }

  // detail 상품 가져오기
  @Operation(summary = "500키워드의 detail 테이블 내용을 가져옴", description = "500키워드의 detail 테이블 내용을 가져옴")
  @GetMapping("/get-up/product-category")
  public List<KeywordDetailEntity> getProductDetail(@RequestParam String keyword)
      throws IOException, GeneralSecurityException, UnirestException {
    return detailGetDataService.getKeywordDetail(keyword);
  }

  // 키워드별 상위 상품 가져오기
  // 백업데이터에서 가져오기
  // 모든 키워드에서 top 100 가져오기
  // 카테고리 별 키워드 가져오기
}
