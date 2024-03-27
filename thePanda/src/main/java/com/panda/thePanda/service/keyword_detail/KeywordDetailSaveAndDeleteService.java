package com.panda.thePanda.service.keyword_detail;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.dto.ListKeywordAndCategoryDTO;
import com.panda.thePanda.entity.keyword_save.KeywordByProduct;
import com.panda.thePanda.entity.keyword_save.KeywordDetailEntity;
import com.panda.thePanda.repository.keyword_save.KeywordByProductsRepository;
import com.panda.thePanda.repository.keyword_save.KeywordDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordDetailSaveAndDeleteService {
  private final NaverSearchAPI searchAPI;
  private final KeywordDetailRepository detailRepository;
  private final KeywordByProductsRepository productsRepository;

  private Integer MAX_DATE_IN_DETAIL = 4;

  public void deleteAllDetailData() {
    detailRepository.deleteAllInBatch();
  }

  public void deleteAllProductData() {
    productsRepository.deleteAllInBatch();
  }

  public Set<String> saveRltKeyword(ListKeywordAndCategoryDTO keywordAndCategoryDTO)
      throws IOException, GeneralSecurityException, UnirestException {
    // 에러 키워드는 중복 될 수 있다. 따라서 중복된 키워드를 제외하도록 Set으로 반환한다.
    Set<String> savedKeyword = new HashSet<>();
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatterIdForDB = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // 사전 저장된 데이터를 가지고 온다.
    String formattedcurrentDateForDB = currentDate.format(formatterIdForDB);
    // 요청 후 대기 시간을 임의로 만든다.
    long sleepMilliSeconds = 450;
    for (String keyword : keywordAndCategoryDTO.getList()) {
      // json을 담을 객체를 생성
      ObjectMapper objectMapperForKeyword = new ObjectMapper();
      // 키워드에 대한 요청
      try {
        String jsonData;
        // api를 요청한다.
        jsonData = searchAPI.getRltKey(keyword);
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
        // 키워드 별 최대 4개씩 저장한다.
        for (int j = 0; j < MAX_DATE_IN_DETAIL; j++) {
          JsonNode itemNode = jsonNode.get(j);
          String searchKeyword = itemNode.get("relKeyword").asText();
          String monthly_pc_qc_cnt = itemNode.get("monthlyPcQcCnt").asText();
          String monthly_mobile_qc_cnt = itemNode.get("monthlyMobileQcCnt").asText();
          int total_qc_cnt = (Integer.parseInt(monthly_pc_qc_cnt) + Integer.parseInt(monthly_mobile_qc_cnt));
          String monthly_ave_pc_cnt = itemNode.get("monthlyAvePcCtr").asText();
          String monthly_ave_mobile_cnt = itemNode.get("monthlyAveMobileCtr").asText();
          String comp_idx = itemNode.get("compIdx").asText();
          // 연관키워드로 가져온 키워드가 현재 top500의 키워드에 있다면
          String checkTop = keyword.equals(searchKeyword) ? "true" : "false";
          String update_date = formattedcurrentDateForDB;
          KeywordDetailEntity entity = new KeywordDetailEntity();

          entity.setId(searchKeyword + keywordAndCategoryDTO.getCategory());
          entity.setKeyword(searchKeyword);
          entity.setMonthly_pc_qc_cnt(monthly_pc_qc_cnt);
          entity.setMonthly_mobile_qc_cnt(monthly_mobile_qc_cnt);
          entity.setTotal_qc_cnt(total_qc_cnt);
          entity.setMonthly_ave_pc_cnt(monthly_ave_pc_cnt);
          entity.setMonthly_ave_mobile_cnt(monthly_ave_mobile_cnt);
          entity.setComp_idx(comp_idx);
          entity.setCategory_id(keywordAndCategoryDTO.getCategory());
          entity.setCheck_top(checkTop);
          entity.setUpdate_date(update_date);
          entity.setCreate_date(update_date);
          // 데이터 저장

          System.out.println(searchKeyword + " 등록" + " " + monthly_pc_qc_cnt);
          savedKeyword.add(searchKeyword);
          detailRepository.save(entity);
        }
      } catch (Exception e) {
        // 검색 했을 때 여러 이유로 실패가 발생한 경우
        if (e.getMessage().contains("Duplicate entry")) {
          System.out.println(keyword + "는 중복된 키워드입니다.");
          continue;
        } else {
          // errorKeyword.add(keyword);
          System.out.println("에러키워드 : " + keyword);
        }
      }
    }
    return savedKeyword;
  }

  // top500의 키워드들을 가져와서 카테고리를 요청하고 db에 저장하는 로직
  public String saveCategoryProduct(Integer _categoryId)
      throws IOException, GeneralSecurityException, UnirestException {
    // 데이터를 가져온다.
    List<KeywordDetailEntity> topKeywords = detailRepository.findByCategoryId(_categoryId);
    // 가져온 데이터를 기반으로 카테고리를 요청한다.
    ObjectMapper objectMapper = new ObjectMapper();
    for (KeywordDetailEntity topKeyword : topKeywords) {
      String keyword = topKeyword.getKeyword();
      Integer categoryId = topKeyword.getCategory_id();
      String checkTop = topKeyword.getCheck_top();
      String monthly_pc_qc_cnt = topKeyword.getMonthly_pc_qc_cnt();
      String monthly_mobile_qc_cnt = topKeyword.getMonthly_mobile_qc_cnt();
      int total_qc_cnt = topKeyword.getTotal_qc_cnt();
      String monthly_ave_pc_cnt = topKeyword.getMonthly_ave_pc_cnt();
      String monthly_ave_mobile_cnt = topKeyword.getMonthly_ave_mobile_cnt();
      String comp_idx = topKeyword.getComp_idx();
      String id = topKeyword.getId();
      String update_date = topKeyword.getUpdate_date();
      String create_date = topKeyword.getCreate_date();
      try {
        // api 요청
        String jsonData;
        jsonData = searchAPI.getShopInfo(keyword);
        JsonNode jsonNode;
        jsonNode = objectMapper.readTree(jsonData);
        JsonNode total = jsonNode.get("total");
        JsonNode itemNode;
        itemNode = jsonNode.get("items");
        itemNode = itemNode.get(0);

        try {
          saveKeywordByProduct(jsonNode, keyword);
        } catch (Exception e) {
          System.out.println(e);
        }
        // 저장할 데이터
        String total_product_count = total.asText();
        String product_name = itemNode.get("title").asText();
        String top_product_link = itemNode.get("image").asText();
        Integer lprice = itemNode.get("lprice").asInt();
        Integer hprice = itemNode.get("hprice").asInt();
        String category1 = itemNode.get("category1").asText();
        String category2 = itemNode.get("category2").asText();
        String category3 = itemNode.get("category3").asText();
        String category4 = itemNode.get("category4").asText();
        String brand = itemNode.get("brand").asText();
        KeywordDetailEntity entity = new KeywordDetailEntity();

        entity.setId(id);
        entity.setKeyword(keyword);
        entity.setTotal_product_count(total_product_count);
        entity.setTop_product_link(top_product_link);
        entity.setCategory1(category1);
        entity.setCategory2(category2);
        entity.setCategory3(category3);
        entity.setCategory4(category4);
        entity.setBrand(brand);
        entity.setProduct_name(product_name);
        entity.setMonthly_pc_qc_cnt(monthly_pc_qc_cnt);
        entity.setMonthly_mobile_qc_cnt(monthly_mobile_qc_cnt);
        entity.setTotal_qc_cnt(total_qc_cnt);
        entity.setMonthly_ave_pc_cnt(monthly_ave_pc_cnt);
        entity.setMonthly_ave_mobile_cnt(monthly_ave_mobile_cnt);
        entity.setComp_idx(comp_idx);
        entity.setCheck_top(checkTop);
        entity.setLprice(lprice);
        entity.setHprice(hprice);
        entity.setCategory_id(categoryId);
        entity.setUpdate_date(update_date);
        entity.setCreate_date(create_date);
        System.out.println(keyword + " update 실행");
        detailRepository.save(entity);

        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      } catch (NullPointerException e) {
        System.out.println(keyword + " errorKeyword!");
        // errorKeyword.add(topKeyword);
      } catch (DuplicateKeyException e) {
        System.out.println(keyword + "는 이미 존재합니다.");
        continue;
      } catch (DataIntegrityViolationException e) {
        System.out.println(keyword + "는 이미 존재합니다.");
        continue;
      } catch (Exception e) {
        continue;
      }

    }

    return "ok";
  }

  public void saveKeywordByProduct(JsonNode jsonNode, String keyword) {

    JsonNode itemNode = jsonNode.get("items");

    for (int i = 0; i < itemNode.size(); i++) {
      JsonNode item = itemNode.get(i);
      String title = item.get("title").asText();
      String image = item.get("image").asText();
      String link = item.get("link").asText();
      Integer lprice = item.get("lprice").asInt();
      Integer hprice = item.get("hprice").asInt();
      String category1 = item.get("category1").asText();
      String category2 = item.get("category2").asText();
      String category3 = item.get("category3").asText();
      String category4 = item.get("category4").asText();
      String brand = item.get("brand").asText();
      Integer rank = i + 1;

      KeywordByProduct product = new KeywordByProduct();
      product.setId(title + rank + keyword);
      product.setTitle(title);
      product.setLink(link);
      product.setImage(image);
      product.setLprice(lprice);
      product.setHprice(hprice);
      product.setCategory1(category1);
      product.setCategory2(category2);
      product.setCategory3(category3);
      product.setCategory4(category4);
      product.setBrand(brand);
      product.setKeyword(keyword);
      product.setRank(rank);

      productsRepository.save(product);

    }
  }

  @Transactional
  public List<String> save_is_season() throws IOException, GeneralSecurityException, UnirestException {
    // 모든 데이터를 가져온다.
    List<KeywordDetailEntity> result = detailRepository.findAll();
    List<String> seasonKeyword = new ArrayList<>();
    List<String> errorKeyword = new ArrayList<>();
    // 100개씩 나누어 저장
    int size = result.size();

    String hasSeasonKeyword = "";
    for (int i = 1; i <= size; i++) {
      if (i % 100 == 1) {
        hasSeasonKeyword = result.get(i - 1).getKeyword();
        continue;
      }
      if ((i % 100) == 0) {
        hasSeasonKeyword += "," + result.get(i - 1).getKeyword();
        seasonKeyword.add(hasSeasonKeyword);
        hasSeasonKeyword = "";
        continue;
      }
      if (i > (size - (size % 100)) && i != size) {
        hasSeasonKeyword += "," + result.get(i - 1).getKeyword();
        continue;
      }
      if (i == size) {
        hasSeasonKeyword += "," + result.get(i - 1).getKeyword();
        seasonKeyword.add(hasSeasonKeyword);
        continue;
      }
      hasSeasonKeyword += "," + result.get(i - 1).getKeyword();
    }

    for (String keyword : seasonKeyword) {
      ObjectMapper objectMapperForKeywordInfoSeason = new ObjectMapper();
      String jsonData = searchAPI.getKeywordInfo(keyword);
      try {
        JsonNode jsonNode = objectMapperForKeywordInfoSeason.readTree(jsonData);
        // jsonNode의 100개 씩 만들어진 데이터를 읽음.
        for (int j = 0; j < jsonNode.size(); j++) {
          JsonNode itemNode = jsonNode.get(j);
          String keywordResult = itemNode.get("keyword").asText();
          itemNode = itemNode.get("managedKeyword");
          String isSeason = itemNode.get("isSeason").asText();
          detailRepository.updateIsSeasonByKeyword(isSeason, keywordResult);
        }
      } catch (Exception e) {
        // 시즌 검색 실패
        errorKeyword.add(keyword);
        continue;
      }
    }
    return errorKeyword;

  }

  @Transactional
  public String save_ad_keyword_cost(Integer category_id)
      throws IOException, GeneralSecurityException, UnirestException {
    List<KeywordDetailEntity> result = detailRepository.findByCategoryId(category_id);
    for (KeywordDetailEntity keywordDetail : result) {
      ObjectMapper objectMapperForKeyword = new ObjectMapper();
      String keyword = keywordDetail.getKeyword();
      String jsonData = searchAPI.getAdKeyword(keyword);
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      try {
        JsonNode jsonNode = objectMapperForKeyword.readTree(jsonData);
        // jsonNode의 100개 씩 만들어진 데이터를 읽음.
        for (int j = 0; j < jsonNode.size(); j++) {
          JsonNode itemNode = jsonNode.get("estimate");
          int minimumCost = 0;
          for (JsonNode item : itemNode) {
            if (item.get("clicks").asInt() >= 100) {
              minimumCost = item.get("bid").asInt();
              break;
            }
          }
          System.out.println(keyword + " 저장");
          detailRepository.updateAdCostByKeyword(minimumCost, keywordDetail.getId());

          if (minimumCost != 0)
            break;
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }
    return "ok";
  }

}
