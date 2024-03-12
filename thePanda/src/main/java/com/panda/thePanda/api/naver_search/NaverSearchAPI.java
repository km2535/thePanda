package com.panda.thePanda.api.naver_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.panda.thePanda.util.signature.SignaturesForNaverAPI;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
  private String rltPath = "/keywordstool";
  private String managedPath = "/ncc/managedKeyword";
  private String shoppingPath = "/search/shop";

  public String requestNaverAPI(String keyword, String apiPath) throws IOException, GeneralSecurityException {
    String encodedKeyword = encodeKeyword(keyword);
    // System.out.println(keyword);
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
      urlString = "https://openapi.naver.com/v1/search/shop.json" + "?query=" + encodedKeyword + "&display=1&start=1";
    }
    URL url = new URL(urlString);
    HttpURLConnection con = null;
    if (apiPath.equals(rltPath) || apiPath.equals(managedPath)) {
      con = setupHttpURLConnection(url, times, signature);
    }
    if (apiPath.equals(shoppingPath)) {
      con = setupHttpURLConnection(url);
    }

    return getResponse(con).toString();
  }

  private String encodeKeyword(String keyword) {
    try {
      return URLEncoder.encode(keyword, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("인코딩 실패.", e);
    }
  }

  private String generateSignature(String times, String apiPath) throws GeneralSecurityException {
    return SignaturesForNaverAPI.of(times, "GET", apiPath, secretKey);
  }

  private HttpURLConnection setupHttpURLConnection(URL url, String times, String signature) throws IOException {
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("X-Timestamp", times);
    con.setRequestProperty("X-API-KEY", accessKey);
    con.setRequestProperty("X-Customer", customerId);
    con.setRequestProperty("X-Signature", signature);
    return con;
  }

  private HttpURLConnection setupHttpURLConnection(URL url) throws IOException {
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("X-Naver-Client-Id", clientId);
    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
    return con;
  }

  private StringBuffer getResponse(HttpURLConnection con) throws IOException {
    int responseCode = con.getResponseCode();
    BufferedReader br;
    // System.out.println("responseCode : " + responseCode);
    if (responseCode == 200) {
      br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
    } else {
      br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
    }
    StringBuffer response = new StringBuffer();
    String inputLine;

    while ((inputLine = br.readLine()) != null) {
      response.append(inputLine);
    }
    br.close();
    return response;
  }

  // 기존 getRltKey와 getKeywordInfo 메소드는 이제 다음처럼 편리하게 호출될 수 있습니다:

  public String getRltKey(String keyword) throws IOException, GeneralSecurityException {
    return requestNaverAPI(keyword, rltPath);
  }

  public String getKeywordInfo(String keyword) throws IOException, GeneralSecurityException {
    return requestNaverAPI(keyword, managedPath);
  }

  public String getShopInfo(String keyword) throws IOException, GeneralSecurityException {
    return requestNaverAPI(keyword, shoppingPath);
  }
}