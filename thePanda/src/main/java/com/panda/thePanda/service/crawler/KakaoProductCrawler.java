package com.panda.thePanda.service.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class KakaoProductCrawler {
  public List<String> getProductNameKakao(String keyword) throws IOException, UnirestException {
    List<String> productLists = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      String urlString = "https://store.kakao.com/a/search/products?q=" + keyword
          + "&searchType=suggest&sort=POPULAR&page=" + i + "&size=50";
      HttpResponse<JsonNode> response = Unirest.get(urlString)
          .header("User-Agent",
              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36")
          .header("X-Shopping-Referrer", "https://store.kakao.com/search/result/product")
          .header("X-Shopping-Tab-Id", "d7420db333461944490cc2")
          .asJson();
      ObjectMapper objectMapper = new ObjectMapper();
      String apiResult = response.getBody().toString();
      com.fasterxml.jackson.databind.JsonNode jsonNode;
      jsonNode = objectMapper.readTree(apiResult);
      jsonNode = jsonNode.get("data").get("contents");

      for (int j = 0; j < jsonNode.size(); j++) {
        com.fasterxml.jackson.databind.JsonNode item = jsonNode.get(j);
        String productName = item.get("productName").asText();
        productLists.add(productName);
      }
    }
    return productLists;
  }
}
