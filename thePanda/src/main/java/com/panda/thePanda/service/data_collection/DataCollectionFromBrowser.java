package com.panda.thePanda.service.data_collection;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.dto.ListKeywordAndCategoryDTO;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBaseErrorRepository;
import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;
import com.panda.thePanda.service.keyword_name.KeywordInfoBaseMethod;
import com.panda.thePanda.service.keyword_name.KeywordInfoMethod;
import com.panda.thePanda.service.keyword_name.KeywordNameMethod;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataCollectionFromBrowser {

  private final KeywordInfoBaseErrorRepository keywordInfoBaseErrorRepository;
  private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;
  private final KeywordInfoMethod keywordInfoMethod;
  private final KeywordNameMethod keywordNameMethod;
  private final KeywordInfoBaseMethod keywordInfoBaseMethod;
  private final NaverSearchAPI naverSearchAPI;

  // 0. keyword_info_(category). 데이터 백업
  public String backupData(String category) {
    keywordInfoMethod.backupData(category);
    return "OK";
  }

  // 1. 데이터 랩에서 자료 수집하고 데이터베이스에 저장하기
  public List<String> saveDatalab(String category) {
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
    List<String> list = keywordNameMethod.keywordGetDatalab(category);
    // 있으면 해당 키워드의 date를 삭제
    if (list.size() > 0) {
      keywordNameMethod.deleteKeywordDatalab(category, category);
    }

    for (String keyword : apiList) {
      keywordNameMethod.saveKeywordDatalab(category, keyword);
    }
    return apiList;
  }

  // 2. 카테고리 상품 검색하고 데이터베이스에 저장하기
  public List<String> saveCategory(ListKeywordAndCategoryDTO list)
      throws IOException, GeneralSecurityException, UnirestException {
    ObjectMapper objectMapper = new ObjectMapper();
    Stack<String> stackForKeywordInfo = new Stack<>();
    List<String> errorKeyword = new ArrayList<>();
    List<String> keywords = list.getList();
    String category = list.getCategory();

    // 외부에서 받은 키워드 스택에 저장
    for (String keyword : keywords) {
      stackForKeywordInfo.push(keyword);
    }
    while (stackForKeywordInfo.size() > 0) {
      // 카테고리 등 상품 정보 db에 저장하기
      String keywordList = stackForKeywordInfo.pop();

      try {
        // api 요청
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

        System.out.println(keywordList + " insert 실행");
        keywordInfoBaseMethod.saveCategoryDates(keyword, total_product_count, top_product_link, category1, category2,
            category3, category4, brand, price, product_name, is_season, category);

        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      } catch (NullPointerException e) {
        System.out.println(keywordList + " errorKeyword!");
        errorKeyword.add(keywordList);
      } catch (DuplicateKeyException e) {
        System.out.println(keywordList + "는 이미 존재합니다.");
        continue;
      } catch (DataIntegrityViolationException e) {
        System.out.println(keywordList + "는 이미 존재합니다.");
        continue;
      }
    }
    // 3. 검색 실패 또는 저장에 실패한 키워드 return
    return errorKeyword;
  }

  // 4. 실패 키워드 다듬고 다시 요청(프론트), 실패 키워드 db에 별도 저장
  public void saveErrorKeyword(ListKeywordAndCategoryDTO keywordAndCategoryDTO) {
    for (String keyword : keywordAndCategoryDTO.getList()) {
      keywordInfoBaseErrorRepository.saveErrorData(keyword, keywordAndCategoryDTO.getCategory());
    }
  }

  // 계절성 상품 여부 저장
  public List<String> savaIsSeason(ListKeywordAndCategoryDTO keywordAndCategoryDTO)
      throws IOException, GeneralSecurityException, UnirestException {
    List<String> seasonKeyword = new ArrayList<>();
    List<String> errorKeyword = new ArrayList<>();
    // 100개씩 나누어 저장
    int size = keywordAndCategoryDTO.getList().size();

    String hasSeasonKeyword = "";
    for (int i = 1; i <= size; i++) {
      if (i % 100 == 1) {
        hasSeasonKeyword = keywordAndCategoryDTO.getList().get(i - 1);
        continue;
      }
      if ((i % 100) == 0) {
        hasSeasonKeyword += "," + keywordAndCategoryDTO.getList().get(i - 1);
        seasonKeyword.add(hasSeasonKeyword);
        hasSeasonKeyword = "";
        continue;
      }
      if (i > (size - (size % 100)) && i != size) {
        hasSeasonKeyword += "," + keywordAndCategoryDTO.getList().get(i - 1);
        continue;
      }
      if (i == size) {
        hasSeasonKeyword += "," + keywordAndCategoryDTO.getList().get(i - 1);
        seasonKeyword.add(hasSeasonKeyword);
        continue;
      }
      hasSeasonKeyword += "," + keywordAndCategoryDTO.getList().get(i - 1);
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
          keywordInfoBaseMethod.isSeasonCheck(keywordAndCategoryDTO.getCategory(), isSeason, keywordResult);
        }
      } catch (Exception e) {
        // 시즌 검색 실패
        errorKeyword.add(keyword);
        continue;
      }
    }
    return errorKeyword;
  }

  // 5. 연관키워드 요청 db저장 -> 요청 실패 키워드 리턴..
  public Set<String> saveKeywordToCmp(ListKeywordAndCategoryDTO keywordAndCategoryDTO) {
    // 에러 키워드는 중복 될 수 있다. 따라서 중복된 키워드를 제외하도록 Set으로 반환한다.
    Set<String> errorKeyword = new HashSet<>();
    // 요청 후 대기 시간을 임의로 만든다.
    long sleepMilliSeconds = 450;
    for (String keyword : keywordAndCategoryDTO.getList()) {
      // json을 담을 객체를 생성
      ObjectMapper objectMapperForKeyword = new ObjectMapper();
      // 키워드에 대한 요청
      try {
        String jsonData;
        // api를 요청한다.
        jsonData = naverSearchAPI.getRltKey(keyword);
        // 요청 후 대기
        try {
          Thread.sleep(sleepMilliSeconds);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        // jsonData를 파싱하여 jsonNode에 담는다.
        JsonNode jsonNode = objectMapperForKeyword.readTree(jsonData);
        jsonNode = jsonNode.get("keywordList");

        // api는 여려 키워드의 정보를 한번에 가져온다.
        for (int j = 0; j < jsonNode.size(); j++) {
          JsonNode itemNode = jsonNode.get(j);
          String searchKeyword = itemNode.get("relKeyword").asText();
          // 연관키워드로 가져온 키워드가 현재 top500의 키워드에 있다면
          if (keywordAndCategoryDTO.getList().contains(searchKeyword)) {
            String monthly_pc_qc_cnt = itemNode.get("monthlyPcQcCnt").asText();
            String monthly_mobile_qc_cnt = itemNode.get("monthlyMobileQcCnt").asText();
            String total_qc_cnt = (Integer.parseInt(monthly_pc_qc_cnt) + Integer.parseInt(monthly_mobile_qc_cnt)) + "";
            String monthly_ave_pc_cnt = itemNode.get("monthlyAvePcCtr").asText();
            String monthly_ave_mobile_cnt = itemNode.get("monthlyAveMobileCtr").asText();
            String comp_idx = itemNode.get("compIdx").asText();

            keywordInfoMethod.saveCmpIdx(searchKeyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt, total_qc_cnt,
                monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx, keywordAndCategoryDTO.getCategory());
            System.out.println(searchKeyword + " 등록");
          } else {
            // 없다면 continue
            System.out.println("연관 키워드는 없음");
            continue;
          }
        }
      } catch (Exception e) {
        // 검색 했을 때 여러 이유로 실패가 발생한 경우
        if (e.getMessage().contains("Duplicate entry")) {
          System.out.println(keyword + "는 중복된 키워드입니다.");
          continue;
        } else {
          errorKeyword.add(keyword);
          System.out.println("에러키워드 : " + keyword);
        }
      }
    }
    return errorKeyword;
  }
  // 6. 완료 후 실패 키워드 다듬고 다시 요청 -> 반복..
  // 7. 5회 이상 반복 실패 시 실패 키워드 db에 별도 저장

}
