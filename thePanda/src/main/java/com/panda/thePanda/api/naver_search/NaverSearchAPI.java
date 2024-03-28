package com.panda.thePanda.api.naver_search;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.util.signature.SignaturesForNaverAPI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NaverSearchAPI {
  @Value("${NAVER.BASE_URL}")
  private String baseUrl;
  @Value("${NAVER.ACCESS.KEY}")
  private String accessKey;
  @Value("${NAVER.SECRET.KEY}")
  private String secretKey;
  @Value("${NAVER.CUSTOMER_ID}")
  private String customerId;
  @Value("${NAVER.CLIENT.ID}")
  private String clientId;
  @Value("${NAVER.CLIENT.SECRET}")
  private String clientSecret;
  @Value("${NAVER2.CLIENT.ID}")
  private String client2Id;
  @Value("${NAVER2.CLIENT.SECRET}")
  private String client2Secret;
  @Value("${NAVER3.CLIENT.ID}")
  private String client3Id;
  @Value("${NAVER3.CLIENT.SECRET}")
  private String client3Secret;

  private String rltPath = "/keywordstool";
  private String managedPath = "/ncc/managedKeyword";
  private String shoppingPath = "/search/shop";
  private String productCount = "getAbroadProduct";
  private String estimate = "/estimate/performance/keyword";

  @SuppressWarnings("null")
  public String requestNaverAPI(String keyword, String apiPath)
      throws IOException, GeneralSecurityException, UnirestException {
    String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
    String times = String.valueOf(System.currentTimeMillis());
    String signature = generateSignature(times, apiPath);
    String urlString = baseUrl;
    if (apiPath.equals(rltPath)) {
      urlString += rltPath + "?hintKeywords=" + encodedKeyword + "&showDetail=1";
    }
    if (apiPath.equals(managedPath)) {
      urlString += managedPath + "?keywords=" + encodedKeyword;
    }
    if (apiPath.equals(shoppingPath)) {
      urlString = "https://openapi.naver.com/v1/search/shop.json" + "?query=" + encodedKeyword
          + "&display=10&start=1";
    }
    if (apiPath.equals(productCount)) {
      urlString = "https://openapi.naver.com/v1/search/shop.json" + "?query=" + encodedKeyword
          + "&display=1&start=1&exclude=cbshop";
    }

    if (apiPath.equals(estimate)) {
      urlString += estimate;
    }

    HttpResponse<JsonNode> response = null;
    if (apiPath.equals(rltPath) || apiPath.equals(managedPath)) {
      response = Unirest.get(urlString)
          .header("X-Timestamp", times)
          .header("X-API-KEY", accessKey)
          .header("X-Customer", customerId)
          .header("X-Signature", signature)
          .asJson();
    }
    if (apiPath.equals(shoppingPath) || apiPath.equals(productCount)) {
      response = Unirest.get(urlString)
          .header("X-Naver-Client-Id", clientId)
          .header("X-Naver-Client-Secret", clientSecret)
          .asJson();
    }

    if (apiPath.equals(estimate)) {
      JSONObject body = new JSONObject();
      List<Integer> bid = new ArrayList<>();
      for (int i = 1; i < 90; i++) {
        bid.add(i * 50);
      }
      body.put("device", "BOTH");
      body.put("keywordplus", true);
      body.put("key", keyword);
      body.put("bids", bid);
      response = Unirest.post(urlString)
          .header("X-Timestamp", times)
          .header("X-API-KEY", accessKey)
          .header("X-Customer", customerId)
          .header("X-Signature", signature)
          .header("Content-Type", "application/json")
          .body(body)
          .asJson();
      // System.out.println(response.getBody());
    }

    return response.getBody().toString();
  }

  private String generateSignature(String times, String apiPath) throws GeneralSecurityException {
    if (apiPath.equals(estimate)) {
      return SignaturesForNaverAPI.of(times, "POST", apiPath, secretKey);
    } else {
      return SignaturesForNaverAPI.of(times, "GET", apiPath, secretKey);
    }
  }

  public String getRltKey(String keyword) throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, rltPath);
  }

  public String getKeywordInfo(String keyword) throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, managedPath);
  }

  public String getShopInfo(String keyword) throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, shoppingPath);
  }

  public String getProductCountExceptAbroad(String keyword)
      throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, productCount);
  }

  public String getAdKeyword(String keyword) throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, estimate);
  }

}
