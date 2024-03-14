package com.panda.thePanda.service.keyword_name;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordNameService {
  private final NaverSearchAPI naverSearchAPI;
  private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;
  private final KeywordNameMethod keywordNameMethod;
  private final KeywordInfoBaseMethod keywordInfoBaseMethod;
  private final KeywordInfoMethod keywordInfoMethod;

  public Set<String> saveKeyword(String category) throws IOException, GeneralSecurityException {
    List<String> list = new ArrayList<>();
    Set<String> result = new HashSet<>();
    List<String> listByKeywordInfoSeason = new ArrayList<>();
    // 데이터 lab api
    System.out.println("데이터 랩 저장 중...");
    list = saveDatalab(category, list);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    // keyword_info_base 저장
    System.out.println("keyword_info_base 저장중...");
    saveCategory(category, list);
    try {
      Thread.sleep(450);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    // 계절 상품인지 확인 후 db 저장
    System.out.println("계절  상품 저장중.");
    listByKeywordInfoSeason = keywordInfoBaseMethod.getKeywords(category);
    System.out.println(listByKeywordInfoSeason.size());
    savaIsSeason(listByKeywordInfoSeason, category);
    try {
      Thread.sleep(450);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    // keyword _base 저장
    System.out.println("클릭율 저장 중....");
    Set<String> setdataStrat = new HashSet<>();
    setdataStrat.addAll(listByKeywordInfoSeason);
    result = saveKeywordToCmp(setdataStrat, 400, category);
    System.out.println("끝");
    return result;
  }

  private List<String> saveDatalab(String category, List<String> list) {
    List<String> apiList = new ArrayList<>();
    for (int i = 1; i < 25 + 1; i++) {
      apiList.addAll(dataLabTopKeywordCrawler.crawlTopKeywordByCategory(category,
          i));
      try {
        Thread.sleep(450);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    // 현재 리스트 키워드 불러오기
    list = keywordNameMethod.keywordGetDatalab(category);
    // 있으면 해당 키워드의 date를 삭제
    if (list.size() > 0) {
      keywordNameMethod.deleteKeywordDatalab(category, category);
    }

    for (String keyword : apiList) {
      // 없다면 createDate와 해당 키워드를 db에 추가
      keywordNameMethod.saveKeywordDatalab(category, keyword);
    }
    return apiList;
  }

  private void saveCategory(String category, List<String> list) throws IOException, GeneralSecurityException {
    ObjectMapper objectMapper = new ObjectMapper();
    Stack<String> stackForKeywordInfo = new Stack<>();
    for (String keyword : list) {
      stackForKeywordInfo.push(keyword);
    }
    // 5회 이상 재요청 실패 시 아웃.
    int stackExit = 50;
    while (stackForKeywordInfo.size() > 0) {
      // 카테고리 등 상품 정보 db에 저장하기
      String keywordList = stackForKeywordInfo.pop();
      System.out.println(keywordList);
      try {
        String jsonData;
        jsonData = (naverSearchAPI.getShopInfo(keywordList));
        JsonNode jsonNode;
        jsonNode = objectMapper.readTree(jsonData);
        JsonNode total = jsonNode.get("total");
        JsonNode itemNode;
        itemNode = jsonNode.get("items");
        itemNode = itemNode.get(0);
        // 저장할 데이터
        String keyword = keywordList;
        String total_product_count = total.asText();
        String product_name = itemNode.get("title").asText();
        String top_product_link = itemNode.get("link").asText();
        String price = itemNode.get("lprice").asText();
        String category1 = itemNode.get("category1").asText();
        String category2 = itemNode.get("category2").asText();
        String category3 = itemNode.get("category3").asText();
        String category4 = itemNode.get("category4").asText();
        String brand = itemNode.get("brand").asText();
        String is_season = "";

        if (keywordInfoBaseMethod.getExistById(category, keyword)) {
          // 존재 한다면 업데이트
          System.out.println("실행");
          keywordInfoBaseMethod.updateDate(
              total_product_count,
              top_product_link,
              brand,
              price,
              product_name,
              keyword,
              category);
        } else {
          // 존재하지 않으면 insert
          System.out.println("insert 실행");
          keywordInfoBaseMethod.saveCategoryDates(keyword, total_product_count, top_product_link, category1, category2,
              category3, category4, brand, price, product_name, is_season, category);
        }
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        stackExit = 5; // 기회 초기화
      } catch (NullPointerException e) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException interrupt) {
          Thread.currentThread().interrupt();
        }
        if (stackExit <= 0) {
          continue;
        } else {
          stackForKeywordInfo.add(keywordList);
          stackExit--;
          continue;
        }
      }
    }
  }

  private void savaIsSeason(List<String> listByKeywordInfoSeason, String category)
      throws IOException, GeneralSecurityException {
    List<String> seasonKeyword = new ArrayList<>();
    // 100개씩 나누어 저장
    int size = listByKeywordInfoSeason.size();

    String hasSeasonKeyword = "";
    for (int i = 1; i <= size; i++) {
      if (i % 100 == 1) {
        hasSeasonKeyword = listByKeywordInfoSeason.get(i - 1);
        continue;
      }
      if ((i % 100) == 0) {
        hasSeasonKeyword += "," + listByKeywordInfoSeason.get(i - 1);
        seasonKeyword.add(hasSeasonKeyword);
        hasSeasonKeyword = "";
        continue;
      }
      if (i > (size - (size % 100)) && i != size) {
        hasSeasonKeyword += "," + listByKeywordInfoSeason.get(i - 1);
        continue;
      }
      if (i == size) {
        hasSeasonKeyword += "," + listByKeywordInfoSeason.get(i - 1);
        seasonKeyword.add(hasSeasonKeyword);
        continue;
      }
      hasSeasonKeyword += "," + listByKeywordInfoSeason.get(i - 1);
    }

    for (String keyword : seasonKeyword) {
      ObjectMapper objectMapperForKeywordInfoSeason = new ObjectMapper();
      String jsonData = naverSearchAPI.getKeywordInfo(keyword);
      try {
        JsonNode jsonNode = objectMapperForKeywordInfoSeason.readTree(jsonData);
        // jsonNode의 100개 씩 만들어진 데이터를 읽음.
        for (int j = 0; j < jsonNode.size(); j++) {
          JsonNode itemNode = jsonNode.get(j);
          String keywordResult = itemNode.get("keyword").asText();
          itemNode = itemNode.get("managedKeyword");
          String isSeason = itemNode.get("isSeason").asText();
          keywordInfoBaseMethod.isSeasonCheck(category, isSeason, keywordResult);
        }
      } catch (Exception e) {
        // 응답으로 보내주자
        // 시즌 검색 실패
      }
    }
  }

  private Set<String> saveKeywordToCmp(Set<String> keywords, long sleepMilliSecond, String category) {
    Set<String> errorKeyword = new HashSet<>();
    Set<String> errorKeywordForreport = new HashSet<>();

    long sleepMilliSeconds = sleepMilliSecond;
    if (sleepMilliSecond > 600)
      return null;
    while (keywords.size() != 0) {
      ObjectMapper objectMapperForKeyword = new ObjectMapper();
      String keyword = keywords.iterator().next();
      if (keyword == null)
        break;
      // 2.set의 size가 0이 될 때까지 api를 요청한다.
      try {
        String jsonData;
        jsonData = naverSearchAPI.getRltKey(keyword);
        try {
          Thread.sleep(sleepMilliSeconds);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        JsonNode jsonNode = objectMapperForKeyword.readTree(jsonData);
        jsonNode = jsonNode.get("keywordList");

        for (int j = 0; j < jsonNode.size(); j++) {
          JsonNode itemNode = jsonNode.get(j);
          String searchKeyword = itemNode.get("relKeyword").asText();
          if (keywords.contains(searchKeyword)) {
            String monthly_pc_qc_cnt = itemNode.get("monthlyPcQcCnt").asText();
            String monthly_mobile_qc_cnt = itemNode.get("monthlyMobileQcCnt").asText();
            String total_qc_cnt = (Integer.parseInt(monthly_pc_qc_cnt) + Integer.parseInt(monthly_mobile_qc_cnt)) + "";
            String monthly_ave_pc_cnt = itemNode.get("monthlyAvePcCtr").asText();
            String monthly_ave_mobile_cnt = itemNode.get("monthlyAveMobileCtr").asText();
            String comp_idx = itemNode.get("compIdx").asText();
            try {
              keywordInfoMethod.saveCmpIdx(searchKeyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt, total_qc_cnt,
                  monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx, category);
              System.out.println(searchKeyword + " 등록");
              keywords.remove(searchKeyword);
            } catch (DuplicateKeyException e) { // 이곳에 사용하는 데이터베이스에 맞는 적절한 예외 유형을 사용하세요.
              System.out.println(searchKeyword + "은/는 이미 데이터베이스에 존재합니다. 다음 키워드로 넘어갑니다.");
              keywords.remove(searchKeyword);
              continue; // 해당 키워드 처리를 건너뛰고 다음 반복으로 넘어감
            }
          } else {
            System.out.println("검색 결과 없음");
            keywords.remove(keyword);
            break;
          }
        }
        sleepMilliSeconds = 400;
      } catch (Exception e) {
        System.out.println("키워드 검색결과 에러");
        keywords.remove(keyword);
        errorKeyword.add(keyword);
        errorKeywordForreport.add(keyword);
        System.out.println("에러키워드 : " + keyword);
        long sleepMilliSec = sleepMilliSecond + 100;
        saveKeywordToCmp(errorKeyword, sleepMilliSec, category);
      }
    }
    return errorKeywordForreport;
  }

}
