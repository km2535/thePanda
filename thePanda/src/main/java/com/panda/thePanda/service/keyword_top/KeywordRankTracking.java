package com.panda.thePanda.service.keyword_top;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.entity.keyword_save.KeywordForRank;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordRankTracking {
  private final NaverSearchAPI searchAPI;

  public HashMap<String, KeywordForRank> trackingRankBymutipleKeyword(String keywords, String mid)
      throws IOException, GeneralSecurityException, UnirestException {
    String[] keywordArr = keywords.split(",");
    HashMap<String, KeywordForRank> results = new HashMap<>();
    for (String keyword : keywordArr) {
      KeywordForRank rank = parseRankingData(keyword, mid);
      results.put(keyword, rank);
    }
    return results;
  }

  private KeywordForRank parseRankingData(String keyword, String mid)
      throws IOException, GeneralSecurityException, UnirestException {
    ObjectMapper objectMapper = new ObjectMapper();
    KeywordForRank rank = new KeywordForRank();
    String jsonData = searchAPI.getRankKeyword(keyword);
    JsonNode jsonNode;
    jsonNode = objectMapper.readTree(jsonData);
    JsonNode itemNode = jsonNode.get("items");

    for (int i = 0; i < itemNode.size(); i++) {
      JsonNode item = itemNode.get(i);
      String productId = item.get("productId").asText();
      if (productId.equals(mid)) {
        setRankingData(rank, item);
        rank.setKeyword(keyword);
        rank.setRank(i + 1);
        break;
      } else {
        setInitData(rank, mid);
      }
    }

    // If the product ID is not found in the first 100 results, search again
    if (rank.getProductId() == null || rank.getProductId().isEmpty() || rank.getProductId().equals("")) {
      String jsonDataAfter100 = searchAPI.getRankAfter100Keyword(keyword);
      jsonNode = objectMapper.readTree(jsonDataAfter100);
      JsonNode itemNodeAfter100 = jsonNode.get("items");

      for (int i = 0; i < itemNodeAfter100.size(); i++) {
        JsonNode item = itemNodeAfter100.get(i);
        String productId = item.get("productId").asText();
        if (productId.equals(mid)) {
          setRankingData(rank, item);
          rank.setRank(i + 101);
          rank.setKeyword(keyword);
          break;
        } else {
          setInitData(rank, mid);
        }
      }
    }
    return rank;
  }

  private void setRankingData(KeywordForRank rank, JsonNode item) {
    rank.setTitle(item.get("title").asText(""));
    rank.setBrand(item.get("brand").asText(""));
    rank.setHprice(item.get("hprice").asInt(0));
    rank.setLprice(item.get("lprice").asInt(0));
    rank.setImage(item.get("image").asText(""));
    rank.setLink(item.get("link").asText(""));
    rank.setCategory1(item.get("category1").asText(""));
    rank.setCategory2(item.get("category2").asText(""));
    rank.setCategory3(item.get("category3").asText(""));
    rank.setCategory4(item.get("category4").asText(""));
    rank.setMaker(item.get("maker").asText(""));
    rank.setMallName(item.get("mallName").asText(""));
    rank.setProductId(item.get("productId").asText(""));
    rank.setProductType(item.get("productType").asText(""));
  }

  private void setInitData(KeywordForRank rank, String mid) {
    rank.setTitle("");
    rank.setBrand("");
    rank.setHprice(0);
    rank.setLprice(0);
    rank.setImage("");
    rank.setLink("");
    rank.setCategory1("");
    rank.setCategory2("");
    rank.setCategory3("");
    rank.setCategory4("");
    rank.setMaker("");
    rank.setMallName("");
    rank.setProductId(mid);
    rank.setProductType("");
    rank.setKeyword("");
    rank.setRank(0);
  }
}
