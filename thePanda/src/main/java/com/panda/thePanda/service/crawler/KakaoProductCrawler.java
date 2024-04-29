package com.panda.thePanda.service.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class KakaoProductCrawler {
  public List<String> getProductNameKakao(String keyword) throws IOException, UnirestException {
    List<String> productLists = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      String urlString = "https://store.kakao.com/a/search/products?q=" + URLEncoder.encode(keyword, "UTF-8")
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

  public List<Map<String, String>> getProductDetailByKeyword(String keyword)
      throws UnirestException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
    List<Map<String, String>> products = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      String urlString = "https://store.kakao.com/a/search/products?q=" + URLEncoder.encode(keyword, "UTF-8")
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
        Map<String, String> productDetail = new HashMap<>();
        com.fasterxml.jackson.databind.JsonNode item = jsonNode.get(j);
        String productName = item.get("productName").asText();
        String categoryName = item.get("categoryName").asText();
        String deliveryFeeType = item.get("deliveryFeeType").asText();
        String originalPrice = item.get("originalPrice").asText();
        String discountedPrice = item.get("discountedPrice").asText();
        String sharingImageUrl = item.get("sharingImageUrl").asText();
        String productPositivePercentage = item.get("productPositivePercentage").asText();
        String reviewCount = item.get("reviewCount").asText();
        String storeName = item.get("storeName").asText();
        String storeLinkPath = item.get("storeLinkPath").asText();
        String freeDelivery = item.get("freeDelivery").asText();
        String newProduct = item.get("new").asText();
        String docId = item.get("docId").asText();
        String productId = item.get("productId").asText();
        String totalSaleCount = item.get("totalSaleCount").asText();
        String groupDiscountUserCount = item.get("groupDiscountUserCount") != null
            ? item.get("groupDiscountUserCount").asText()
            : "0";
        productDetail.put("productName", productName);
        productDetail.put("categoryName", categoryName);
        productDetail.put("deliveryFeeType", deliveryFeeType);
        productDetail.put("originalPrice", originalPrice);
        productDetail.put("discountedPrice", discountedPrice);
        productDetail.put("sharingImageUrl", sharingImageUrl);
        productDetail.put("productPositivePercentage", productPositivePercentage);
        productDetail.put("storeName", storeName);
        productDetail.put("reviewCount", reviewCount);
        productDetail.put("groupDiscountUserCount", groupDiscountUserCount);
        productDetail.put("freeDelivery", freeDelivery);
        productDetail.put("newProduct", newProduct);
        productDetail.put("totalSaleCount", totalSaleCount);
        productDetail.put("storeLinkPath",
            "https://store.kakao.com" + storeLinkPath + "/products/" + productId + "?docId=" + docId);
        products.add(productDetail);
      }
    }
    return products;
  }
}