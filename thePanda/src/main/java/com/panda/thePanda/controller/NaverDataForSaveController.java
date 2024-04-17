package com.panda.thePanda.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.api.naver_search.NaverDataLabAPI;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.dto.ListKeywordAndCategoryDTO;
import com.panda.thePanda.entity.keyword_save.KeywordByProduct;
import com.panda.thePanda.entity.keyword_save.KeywordDetailBackupEntity;
import com.panda.thePanda.entity.keyword_save.KeywordForRank;
import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;
import com.panda.thePanda.service.crawler.NaverProductNameCrawler;
import com.panda.thePanda.service.keyword_detail.KeywordDetailBackupService;
import com.panda.thePanda.service.keyword_detail.KeywordDetailGetDataService;
import com.panda.thePanda.service.keyword_detail.KeywordDetailSaveAndDeleteService;
import com.panda.thePanda.service.keyword_detail.ProductPurcharedService;
import com.panda.thePanda.service.keyword_top.KeywordRankTracking;
import com.panda.thePanda.service.keyword_top.KeywordTopService;
import com.panda.thePanda.service.keyword_top.ProductListByKeywordService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/panda-v1/admin/naver")
public class NaverDataForSaveController {
  private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;
  private final KeywordTopService keywordSaveService;
  private final KeywordDetailSaveAndDeleteService saveAndDeleteService;
  private final KeywordDetailGetDataService detailGetDataService;
  private final NaverSearchAPI searchAPI;
  private final NaverDataLabAPI dataLabAPI;
  private final KeywordDetailBackupService backupService;
  private final ProductListByKeywordService productListByKeywordService;
  private final NaverProductNameCrawler naverProductNameCrawler;
  private final ProductPurcharedService purcharedService;
  private final KeywordRankTracking rankTracking;

  @Operation(summary = "500키워드 테이블 삭제", description = "테이블의 모든 데이터 삭제.")
  @DeleteMapping("/delete/top-keyword")
  public void deleteEveryCategoryProduct(@RequestParam int category) throws IOException {
    keywordSaveService.deleteAllData();
  }

  @Operation(summary = "키워드 결과 삭제", description = "테이블의 모든 데이터 삭제.")
  @DeleteMapping("/delete/detail-keyword")
  public void deleteEveryDetailProduct(@RequestParam int category) throws IOException {
    saveAndDeleteService.deleteAllDetailData();
  }

  @Operation(summary = "모든 product리스트 삭제", description = "테이블의 모든 데이터 삭제.")
  @DeleteMapping("/delete/detail-product")
  public void deleteEveryProductList() throws IOException {
    saveAndDeleteService.deleteAllProductData();
  }

  @Operation(summary = "인기 검색어 받기 (top 500 키워드)", description = "모든 카테고리를 받아 db에 저장합니다.")
  @PutMapping("/save/top-keyword")
  public List<String> saveEveryCategoryProduct(@RequestParam int category) throws IOException {
    return keywordSaveService.searchAndSave(category);
  }

  @Operation(summary = "키워드 클릭률을 저장합니다.", description = "검색 수, 클릭률 반환")
  @PutMapping("/save-data/product-click")
  public Set<String> getClickCountProduct(@RequestBody ListKeywordAndCategoryDTO requestDto)
      throws IOException, GeneralSecurityException, UnirestException {
    // 500 키워드를 저장하고 바로 이어서 요청해야 합니다.
    return saveAndDeleteService.saveRltKeyword(requestDto);
  }

  @Operation(summary = "키워드 카테고리와 상품 순위를 저장합니다.", description = "키워드별 상품 순위와 1순위 카테고리를 저장합니다.")
  @PutMapping("/save-data/product-category")
  public String getProductCategory(@RequestParam int _categoryId)
      throws IOException, GeneralSecurityException, UnirestException {
    // detail 테이블을 가져와서 카테고리를 갱신하고 20위 상품 데이터를 별도로 저장합니다.
    saveAndDeleteService.saveCategoryProduct(_categoryId);
    return "OK";
  }

  // 계절성 상품을 갱신하는 함수
  @Operation(summary = "계절성 키워드 여부 확인.", description = "계절성 키워드 여부를 확인합니다.")
  @PutMapping("/save-data/product-season")
  public List<String> saveProductIsSeason() throws IOException, GeneralSecurityException, UnirestException {
    return saveAndDeleteService.save_is_season();
  }

  // 광고비용 갱신하는 함수
  @Operation(summary = "광고비용 갱신", description = "광고 비용을 갱신합니다.")
  @PutMapping("/save-data/product-adcost")
  public String saveProductAdCost(
      @RequestParam int _categoryId) throws IOException, GeneralSecurityException, UnirestException {
    return saveAndDeleteService.save_ad_keyword_cost(_categoryId);
  }

  @Operation(summary = "500키워드의 detail 테이블 내용을 백업", description = "500키워드의 detail 테이블 내용을 백업")
  @PutMapping("/back-up/product-category")
  public String backupProductCategory(
      @RequestParam Integer _categoryId) throws IOException, GeneralSecurityException, UnirestException {
    backupService.backup_data(_categoryId);
    return "OK";
  }

  // detail 상품 가져오기
  @Operation(summary = "500키워드의 detail 테이블 내용을 가져옴", description = "500키워드의 detail 테이블 내용을 가져옴")
  @GetMapping("/get-up/product-category")
  public List<KeywordDetailBackupEntity> getProductDetail(@RequestParam String keyword)
      throws IOException, GeneralSecurityException, UnirestException {
    return detailGetDataService.getKeywordDetail(keyword);
  }

  // brand이면서 특정 카테고리의 상품
  @Operation(summary = "TopKeyword를 기반으로 특정 카테고리의 keywordDetail을 가져옴", description = "TopKeyword를 기반으로 keywordDetail을 가져옴")
  @GetMapping("/get-keyword/product-detail")
  public List<KeywordDetailBackupEntity> getProductDetailTopKeyword(@RequestParam int categoryId,
      @RequestParam String sort)
      throws IOException, GeneralSecurityException, UnirestException {
    List<KeywordDetailBackupEntity> result = new ArrayList<>();
    switch (sort) {
      case "trend-keyword":
        result = detailGetDataService.getTopKeywordDetail(categoryId);
        break;
      case "rising-keyword":
        result = detailGetDataService.getTopKeywordDetailSortByDiffTotalQcCnt(categoryId);
        break;
      case "new-keyword":
        result = detailGetDataService.getTopKeywordDetailSortByIsNew(categoryId);
        break;

    }
    return result;
  }

  // brand가 아니면서 특정 카테고리의 상품
  @Operation(summary = "brand가 아닌 특정 카테고리의 top 상품", description = "brand가 아닌 특정 카테고리 상품들로 가져옴")
  @GetMapping("/get-keyword/product-no-brand")
  public List<KeywordDetailBackupEntity> getAllProductDetailTopKeywordNotBrand(
      @RequestParam int categoryId,
      @RequestParam String sort)
      throws IOException, GeneralSecurityException, UnirestException {
    List<KeywordDetailBackupEntity> result = new ArrayList<>();
    switch (sort) {
      case "trend-keyword":
        result = detailGetDataService.getKeywordDetailNotBrand(categoryId);
        break;
      case "rising-keyword":
        result = detailGetDataService.getKeywordDetailNotBrandSortByDiffTotalQcCnt(categoryId);
        break;
      case "new-keyword":
        result = detailGetDataService.getKeywordDetailNotBrandSortByIsNew(categoryId);
        break;

    }
    return result;
  }

  // 모든 키워드에서 top 150 가져오기
  @Operation(summary = "TopKeyword를 기반으로 keywordDetail을 가져옴", description = "TopKeyword를 기반으로 keywordDetail을 가져옴")
  @GetMapping("/get-all/product-detail")
  public List<KeywordDetailBackupEntity> getAllProductDetailTopKeyword(
      @RequestParam String sort)
      throws IOException, GeneralSecurityException, UnirestException {
    List<KeywordDetailBackupEntity> result = new ArrayList<>();
    switch (sort) {
      case "trend-keyword":
        result = detailGetDataService.getAllKeywordDetail();
        break;
      case "rising-keyword":
        result = detailGetDataService.getAllKeywordDetailSortByDiffTotalQcCnt();
        break;
      case "new-keyword":
        result = detailGetDataService.getAllKeywordDetailSortByIsNew();
        break;

    }
    return result;
  }

  // brand가 아닌 상품들로 골라내기
  @Operation(summary = "brand가 아닌 모든 top 상품", description = "brand가 아닌 모든 상품들로 가져옴")
  @GetMapping("/get-all/product-no-brand")
  public List<KeywordDetailBackupEntity> getAllProductDetailTopKeywordNotBrand(
      @RequestParam String sort)
      throws IOException, GeneralSecurityException, UnirestException {
    List<KeywordDetailBackupEntity> result = new ArrayList<>();
    switch (sort) {
      case "trend-keyword":
        result = detailGetDataService.getAllKeywordDetailNotBrand();
        break;
      case "rising-keyword":
        result = detailGetDataService.getAllKeywordDetailNotBrandSortByDiffTotalQcCnt();
        break;
      case "new-keyword":
        result = detailGetDataService.getAllKeywordDetailNotBrandSortByIsNew();
        break;

    }
    return result;
  }

  // 키워드별 상위 상품 가져오기
  @Operation(summary = "키워드로 상위 상품을 가져옴", description = "키워드로 상위 상품을 가져옴")
  @GetMapping("/get-up/product-List")
  public List<KeywordByProduct> getProductListByKeyword(@RequestParam String keyword)
      throws IOException, GeneralSecurityException, UnirestException {
    return productListByKeywordService.getProductListByKeyword(keyword);
  }

  // 키워드로 해외 상품을 제외한 상품 갯수 가져오기
  @Operation(summary = "키워드로 해외 상품을 제외한 상품 갯수 가져오기", description = "키워드로 해외 상품을 제외한 상품 갯수 가져오기")
  @GetMapping("/get-product/except-abroad")
  public Integer getProductCountExceptAbroad(@RequestParam String keyword)
      throws IOException, GeneralSecurityException, UnirestException {
    return detailGetDataService.getProductCountExceptAbroad(keyword);
  }

  // 데이터 랩에서 키워드 검색결과 가져오기
  @Operation(summary = "데이터 랩에서 키워드 검색결과 가져오기", description = "데이터 랩에서 키워드 검색결과 가져오기")
  @GetMapping("/get-datalab/search-result")
  public String getShoppingInsightSearchCount(@RequestParam String keyword,
      @RequestParam String categoryId,
      @RequestParam Integer ago,
      @RequestParam String type)
      throws IOException, GeneralSecurityException, UnirestException {
    return dataLabAPI.getShoppingInsightSearchCount(keyword, categoryId, ago, type);
  }

  // 데이터 랩에서 연령별 키워드 검색결과 가져오기
  @Operation(summary = "데이터 랩에서 연령별 키워드 검색결과 가져오기", description = "데이터 랩에서 연령별 키워드 검색결과 가져오기")
  @GetMapping("/get-datalab/search-age")
  public String getShoppingInsightAgeCount(@RequestParam String keyword,
      @RequestParam String categoryId)
      throws IOException, GeneralSecurityException, UnirestException {
    return dataLabAPI.getShoppingInsightAgeCount(keyword, categoryId);
  }

  // 데이터 랩에서 기기별 키워드 검색결과 가져오기
  @Operation(summary = "데이터 랩에서 기기별 키워드 검색결과 가져오기", description = "데이터 랩에서 기기별 키워드 검색결과 가져오기")
  @GetMapping("/get-datalab/search-device")
  public String getShoppingInsightDeviceCount(@RequestParam String keyword,
      @RequestParam String categoryId)
      throws IOException, GeneralSecurityException, UnirestException {
    return dataLabAPI.getShoppingInsightDeviceCount(keyword, categoryId);
  }

  // 데이터 랩에서 성별 키워드 검색결과 가져오기
  @Operation(summary = "데이터 랩에서 성별 키워드 검색결과 가져오기", description = "데이터 랩에서 성별 키워드 검색결과 가져오기")
  @GetMapping("/get-datalab/search-gender")
  public String getShoppingInsightGenderCount(@RequestParam String keyword,
      @RequestParam String categoryId)
      throws IOException, GeneralSecurityException, UnirestException {
    return dataLabAPI.getShoppingInsightGenderCount(keyword, categoryId);
  }

  // 크롤링 하여 top80 제품의 총 구매수량, 가격 가져오기
  @Operation(summary = "크롤링 하여 top80 제품의 총 구매수량, 가격 가져오기", description = "크롤링 하여 top80 제품의 총 구매수량, 가격 가져오기")
  @Deprecated
  @GetMapping("/get-crawler/search-product")
  public HashMap<String, Integer> getSalesProductList(@RequestParam String keyword) {
    return purcharedService.getPurcharsedCount(keyword);
  }

  // 네이버 mid 상품번호를 이용한 순위 추적
  @Operation(summary = "네이버 mid 상품번호를 이용한 순위 추적", description = "검색한 상품의 순위를 추적합니다. ")
  @GetMapping("/naver-tracking/rank")
  public HashMap<String, KeywordForRank> getNaverSearchShopAPI(
      @RequestParam String keywords, @RequestParam String midNumber)
      throws IOException, GeneralSecurityException, UnirestException {
    return rankTracking.trackingRankBymutipleKeyword(keywords, midNumber);
  }
}
