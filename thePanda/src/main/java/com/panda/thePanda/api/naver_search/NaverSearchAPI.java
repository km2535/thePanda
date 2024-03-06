package com.panda.thePanda.api.naver_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
  private String path = "/keywordstool";

  public StringBuffer getRltKey(String keyword) throws IOException {
    String times = String.valueOf(System.currentTimeMillis());
    String parameter = "hintKeywords=";
    StringBuffer response = new StringBuffer();
    try {
      keyword = URLEncoder.encode(keyword, "UTF-8");
      // keyword = URLEncoder.encode(keyword, "EUC-KR");
      System.out.println("keyword : " + keyword);
    } catch (Exception e) {
      throw new RuntimeException("인코딩 실패.");
    }
    try {
      URL url = new URL(baseUrl + path + "?" + parameter + keyword + "&showDetail=1");
      System.out.println("url : " + url);

      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      String signature = SignaturesForNaverAPI.of(times, "GET", path, secretKey);
      con.setRequestMethod("GET");
      con.setRequestProperty("X-Timestamp", times);
      con.setRequestProperty("X-API-KEY", accessKey);
      con.setRequestProperty("X-Customer", customerId);
      con.setRequestProperty("X-Signature", signature);
      con.setDoOutput(true);
      System.out.println("url : " + signature);

      int responseCode = con.getResponseCode();
      BufferedReader br;
      System.out.println("responseCode : " + responseCode);
      if (responseCode == 200) {
        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
      } else {
        br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
      }
      String inputLine;

      while ((inputLine = br.readLine()) != null) {
        response.append(inputLine);
      }
      br.close();
      // System.out.println("Response : " + response.toString());

    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }
    return response;
  }
}
