package com.panda.thePanda.controller;

import java.io.UnsupportedEncodingException;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.service.crawler.KakaoProductCrawler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/kakao")
@RequiredArgsConstructor
public class KakaoProductController {
  private final KakaoProductCrawler kakaoProductCrawler;

  @GetMapping("/product/detail")
  public Map<String, List<Map<String, String>>> getProductInfoByMultipleKeywords(
      @RequestParam String keywords)
      throws JsonMappingException, JsonProcessingException, UnirestException, UnsupportedEncodingException {
    Map<String, List<Map<String, String>>> result = new HashMap<>();
    String[] keywordsArr = keywords.split(",");
    for (String item : keywordsArr) {
      String keyword = item.trim();
      List<Map<String, String>> productResult = kakaoProductCrawler
          .getProductDetailByKeyword(keyword);
      result.put(keyword, productResult);
    }
    return result;
  }
}
