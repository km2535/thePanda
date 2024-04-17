package com.panda.thePanda.service.keyword_top;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTitleByKeyword {
  private final NaverSearchAPI naverSearchAPI;

  public List<String> getNaverSpProductByKeyword(String keyword)
      throws IOException, GeneralSecurityException, UnirestException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonData = naverSearchAPI.getRankKeyword(keyword);
    JsonNode jsonNode;
    jsonNode = objectMapper.readTree(jsonData);
    JsonNode itemNode = jsonNode.get("items");
    List<String> productList = new ArrayList<String>();

    for (int i = 0; i < itemNode.size(); i++) {
      JsonNode item = itemNode.get(i);
      String title = item.get("title").asText();
      productList.add(title);
    }
    return productList;
  }
}
