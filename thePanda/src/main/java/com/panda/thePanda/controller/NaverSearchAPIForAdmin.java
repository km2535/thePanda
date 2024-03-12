package com.panda.thePanda.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/panda-v1/admin")
public class NaverSearchAPIForAdmin {
  private final NaverSearchAPI naverSearchAPI;
  private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;

  public NaverSearchAPIForAdmin(
      NaverSearchAPI naverSearchAPI, DataLabTopKeywordCrawler dataLabTopKeywordCrawler) {
    this.naverSearchAPI = naverSearchAPI;
    this.dataLabTopKeywordCrawler = dataLabTopKeywordCrawler;
  }

  @Operation(summary = "키워드와 연관키워드들의 월간 키워드 검색량", description = "키워드와 연관 키워드들의 월간 키워드 검색량과 경쟁률를 리턴합니다.")
  @PostMapping("/naver-search/keyword")
  public StringBuffer getNaverSearchAPI(
      @RequestBody String[] keywords) throws IOException, GeneralSecurityException {
    StringBuffer response = new StringBuffer();
    for (String keyword : keywords) {
      response.append(naverSearchAPI.getRltKey(keyword));
    }
    return response;
  }

  @Operation(summary = "카테고리와 총 상품 수, 브랜드", description = "검색 한 키워드의 총 상품수와 카테고리를 리턴합니다. ")
  @PostMapping("/naver-shopping/keyword")
  public StringBuffer getNaverSearchShopAPI(
      @RequestBody String[] keywords) throws IOException, GeneralSecurityException {
    StringBuffer response = new StringBuffer();
    for (String keyword : keywords) {
      response.append(naverSearchAPI.getShopInfo(keyword));
    }
    return response;
  }

  @Operation(summary = "계절성 키워드 확인", description = "성인 키워드, 금지, 계절성을 여부를 리턴합니다. 키워드 간 콤마로 구분하여 요청 받습니다.")
  @PostMapping("/naver-managed/keyword")
  public StringBuffer getNaverSearchManagedKeywordAPI(
      @RequestBody String keyword) throws IOException, GeneralSecurityException {
    StringBuffer response = new StringBuffer();
    response.append(naverSearchAPI.getKeywordInfo(keyword));

    return response;
  }

  @Operation(summary = "인기 검색어 받기 (top 500 키워드)", description = "페이지와 카테고리를 입력 받아 상위 키워드를 검색하여 리스트를 전달합니다.")
  @GetMapping("/page-by/top-keyword")
  public List<String> getDataLabTopKeywordByCategoryAndPage(
      @RequestParam String category, int page, int requireMilliSeconds) throws IOException {
    List<String> response = new ArrayList<>();
    for (int i = 1; i < page + 1; i++) {
      response.addAll(dataLabTopKeywordCrawler.crawlTopKeywordByCategory(category, i));
      try {
        Thread.sleep(requireMilliSeconds);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    return response;
  }
}
