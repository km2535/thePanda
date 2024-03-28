package com.panda.thePanda.api.naver_search;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class NaverDataLabAPI {

  @Value("${NAVER2.CLIENT.ID}")
  private String client2Id;
  @Value("${NAVER2.CLIENT.SECRET}")
  private String client2Secret;
  @Value("${NAVER3.CLIENT.ID}")
  private String client3Id;
  @Value("${NAVER3.CLIENT.SECRET}")
  private String client3Secret;

  private String getShoppingInsightSearchCount = "getShoppingInsightSearchCount";
  private String getShoppingInsightAgeCount = "getShoppingInsightAgeCount";
  private String getShoppingInsightDeviceCount = "getShoppingInsightDeviceCount";
  private String getShoppingInsightGenderCount = "getShoppingInsightGenderCount";
  private String today = getTodayDate();

  @SuppressWarnings("null")
  public String requestNaverAPI(String keyword, String apiPath, String categoryId, Integer ago, String type)
      throws UnirestException, UnsupportedEncodingException {
    String category = getCategory(categoryId);
    String urlString = "https://openapi.naver.com/v1/datalab/shopping/category";
    HttpResponse<JsonNode> response = null;

    // 트랜드 조회
    if (apiPath.equals(getShoppingInsightSearchCount)) {
      urlString += "/keywords";
      JSONObject body = new JSONObject();
      body.put("startDate", getYearsAgo(ago));
      body.put("endDate", today);
      body.put("timeUnit", type);
      body.put("category", categoryId);
      JSONArray keywordArray = new JSONArray();
      JSONObject keywordObject = new JSONObject();
      keywordObject.put("name", category);
      JSONArray paramArray = new JSONArray();
      paramArray.put(keyword);
      keywordObject.put("param", paramArray);
      keywordArray.put(keywordObject);
      body.put("keyword", keywordArray);
      body.put("device", "");
      body.put("gender", "");
      JSONArray agesArray = new JSONArray();
      body.put("ages", agesArray);
      response = responsePassing(body, urlString, response, client2Id, client2Secret);
    }

    // 연령별 트랜드 조회
    if (apiPath.equals(getShoppingInsightAgeCount)) {
      urlString += "/keyword/age";
      JSONObject body = new JSONObject();
      body.put("startDate", getYearsAgo(ago));
      body.put("endDate", today);
      body.put("timeUnit", type);
      body.put("category", categoryId);
      body.put("keyword", keyword);
      body.put("device", "");
      body.put("gender", "");
      response = responsePassing(body, urlString, response, client2Id, client2Secret);
    }

    // 기기별 트랜드 조회와 성별 트랜드 조회
    if (apiPath.equals(getShoppingInsightDeviceCount) || apiPath.equals(getShoppingInsightGenderCount)) {

      if (apiPath.equals(getShoppingInsightDeviceCount))
        urlString += "/keyword/device";
      if (apiPath.equals(getShoppingInsightGenderCount))
        urlString += "/keyword/gender";

      JSONObject body = new JSONObject();
      body.put("startDate", getYearsAgo(ago));
      body.put("endDate", today);
      body.put("timeUnit", type);
      body.put("category", categoryId);
      body.put("keyword", keyword);
      body.put("device", "");
      body.put("gender", "");
      JSONArray agesArray = new JSONArray();
      body.put("ages", agesArray);
      response = responsePassing(body, urlString, response, client3Id, client3Secret);

    }

    return response.getBody().toString();
  }

  public String getShoppingInsightSearchCount(String keyword, String categoryId, Integer ago, String type)
      throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, getShoppingInsightSearchCount, categoryId, ago, type);
  }

  public String getShoppingInsightAgeCount(String keyword, String categoryId)
      throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, getShoppingInsightAgeCount, categoryId, 1, "month");
  }

  public String getShoppingInsightDeviceCount(String keyword, String categoryId)
      throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, getShoppingInsightDeviceCount, categoryId, 1, "month");
  }

  public String getShoppingInsightGenderCount(String keyword, String categoryId)
      throws IOException, GeneralSecurityException, UnirestException {
    return requestNaverAPI(keyword, getShoppingInsightGenderCount, categoryId, 1, "month");
  }

  // 오늘 날짜, 1년전 날짜 구하기
  private String getTodayDate() {
    LocalDate today = LocalDate.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedToday = today.format(formatter);

    return formattedToday;
  }

  private String getYearsAgo(int ago) {
    LocalDate today = LocalDate.now();
    // 날짜를 yyyy-MM-dd 형식으로 포맷팅
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // 1년 전 날짜 계산
    LocalDate oneYearAgo = today.minusYears(ago);
    if (ago == 0) {
      oneYearAgo = today.minusMonths(1);
    }
    String formattedYearAgo = oneYearAgo.format(formatter);

    return formattedYearAgo;
  }

  // 카테고리 아이디별 단어 찾기
  private String getCategory(String categoryId) {
    HashMap<String, String> data = new HashMap<>();
    switch (categoryId) {
      case "50000000":
        data.put(categoryId, "패션의류");
        break;
      case "50000001":
        data.put(categoryId, "패션잡화");
        break;
      case "50000002":
        data.put(categoryId, "화장품/미용");
        break;
      case "50000003":
        data.put(categoryId, "디지털/가전");
        break;
      case "50000004":
        data.put(categoryId, "가구/인테리어");
        break;
      case "50000005":
        data.put(categoryId, "출산/육아");
        break;
      case "50000006":
        data.put(categoryId, "식품");
        break;
      case "50000007":
        data.put(categoryId, "스포츠/레저");
        break;
      case "50000008":
        data.put(categoryId, "생활/건강");
        break;
      case "50000009":
        data.put(categoryId, "여가/생활편의");
        break;
    }

    return data.get(categoryId);
  }

  private HttpResponse<JsonNode> responsePassing(JSONObject body, String urlString, HttpResponse<JsonNode> response,
      String client,
      String secret) throws UnirestException {
    response = Unirest.post(urlString)
        .header("X-Naver-Client-Id", client)
        .header("X-Naver-Client-Secret", secret)
        .header("Content-Type", "application/json")
        .body(body)
        .asJson();
    return response;
  }
}
