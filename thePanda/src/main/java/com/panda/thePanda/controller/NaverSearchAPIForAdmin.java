package com.panda.thePanda.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/panda-v1/admin")
public class NaverSearchAPIForAdmin {
  private final NaverSearchAPI naverSearchAPI;

  public NaverSearchAPIForAdmin(
      NaverSearchAPI naverSearchAPI) {
    this.naverSearchAPI = naverSearchAPI;
  }

  @Operation(summary = "키워드와 연관키워드들의 월간 키워드 검색량", description = "키워드와 연관 키워드들의 월간 키워드 검색량과 경쟁률를 리턴합니다.")
  @PostMapping("/naver-search/keyword")
  public StringBuffer getNaverSearchAPI(
      @RequestBody String[] keywords) throws IOException {
    StringBuffer response = new StringBuffer();
    for (String keyword : keywords) {
      response.append(naverSearchAPI.getRltKey(keyword));
    }
    return response;
  }
}
