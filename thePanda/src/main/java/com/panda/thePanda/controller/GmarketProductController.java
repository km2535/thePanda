package com.panda.thePanda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.panda.thePanda.service.crawler.GmarketProductNameCrawler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/gmarket")
@RequiredArgsConstructor
public class GmarketProductController {
  private final GmarketProductNameCrawler nameCrawler;

  @GetMapping("/product/detail")
  public Map<String, List<Map<String, String>>> getProductInfoByMultipleKeywords(
      @RequestParam String keywords) {
    Map<String, List<Map<String, String>>> result = new HashMap<>();
    String[] keywordsArr = keywords.split(",");
    for (String item : keywordsArr) {
      String keyword = item.trim();
      List<Map<String, String>> productResult = nameCrawler
          .getProductDetailByKeyword(keyword);
      result.put(keyword, productResult);
    }
    return result;
  }
}