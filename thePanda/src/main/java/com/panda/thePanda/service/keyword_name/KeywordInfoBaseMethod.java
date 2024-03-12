package com.panda.thePanda.service.keyword_name;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000000Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000001Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000002Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000003Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000004Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000005Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000006Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000007Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000008Repository;
import com.panda.thePanda.repository.keyword_info_base.KeywordInfoBase50000009Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordInfoBaseMethod {
  private final KeywordInfoBase50000000Repository KeywordInfoBase5000000Repository;
  private final KeywordInfoBase50000001Repository KeywordInfoBase5000001Repository;
  private final KeywordInfoBase50000002Repository KeywordInfoBase5000002Repository;
  private final KeywordInfoBase50000003Repository KeywordInfoBase5000003Repository;
  private final KeywordInfoBase50000004Repository KeywordInfoBase5000004Repository;
  private final KeywordInfoBase50000005Repository KeywordInfoBase5000005Repository;
  private final KeywordInfoBase50000006Repository KeywordInfoBase5000006Repository;
  private final KeywordInfoBase50000007Repository KeywordInfoBase5000007Repository;
  private final KeywordInfoBase50000008Repository KeywordInfoBase5000008Repository;
  private final KeywordInfoBase50000009Repository KeywordInfoBase5000009Repository;

  public boolean getExistById(String category, String keywordList) {
    boolean result = false;
    switch (category) {
      case "50000000":
        result = KeywordInfoBase5000000Repository.existsById(keywordList);
        break;
      case "50000001":
        result = KeywordInfoBase5000001Repository.existsById(keywordList);
        break;
      case "50000002":
        result = KeywordInfoBase5000002Repository.existsById(keywordList);
        break;
      case "50000003":
        result = KeywordInfoBase5000003Repository.existsById(keywordList);
        break;
      case "50000004":
        result = KeywordInfoBase5000004Repository.existsById(keywordList);
        break;
      case "50000005":
        result = KeywordInfoBase5000005Repository.existsById(keywordList);
        break;
      case "50000006":
        result = KeywordInfoBase5000006Repository.existsById(keywordList);
        break;
      case "50000007":
        result = KeywordInfoBase5000007Repository.existsById(keywordList);
        break;
      case "50000008":
        result = KeywordInfoBase5000008Repository.existsById(keywordList);
        break;
      case "50000009":
        result = KeywordInfoBase5000009Repository.existsById(keywordList);
        break;
    }
    return result;
  }

  public void updateDate(String total_product_count,
      String top_product_link,
      String brand,
      String price,
      String product_name,
      String keyword, String category) {
    switch (category) {
      case "50000000":
        KeywordInfoBase5000000Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000001":
        KeywordInfoBase5000001Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000002":
        KeywordInfoBase5000002Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000003":
        KeywordInfoBase5000003Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000004":
        KeywordInfoBase5000004Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000005":
        KeywordInfoBase5000005Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000006":
        KeywordInfoBase5000006Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000007":
        KeywordInfoBase5000007Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000008":
        KeywordInfoBase5000008Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
      case "50000009":
        KeywordInfoBase5000009Repository.updateUpdateDate(total_product_count, top_product_link, brand, price,
            product_name, keyword);
        break;
    }

  }

  public void saveCategoryDates(String keyword,
      String total_product_count,
      String top_product_link,
      String category1,
      String category2,
      String category3,
      String category4,
      String brand,
      String price,
      String product_name,
      String is_season,
      String category) {
    switch (category) {
      case "50000000":
        KeywordInfoBase5000000Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000001":
        KeywordInfoBase5000001Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000002":
        KeywordInfoBase5000002Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000003":
        KeywordInfoBase5000003Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000004":
        KeywordInfoBase5000004Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000005":
        KeywordInfoBase5000005Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000006":
        KeywordInfoBase5000006Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000007":
        KeywordInfoBase5000007Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000008":
        KeywordInfoBase5000008Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
      case "50000009":
        KeywordInfoBase5000009Repository.saveCategoryData(keyword, total_product_count, top_product_link, category1,
            category2, category3, category4, brand, price, product_name, is_season);
        break;
    }

  }

  public void isSeasonCheck(String category, String isSeason, String keywordResult) {
    System.out.println(keywordResult + " " + isSeason);
    switch (category) {
      case "50000000":
        KeywordInfoBase5000000Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000001":
        KeywordInfoBase5000001Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000002":
        KeywordInfoBase5000002Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000003":
        KeywordInfoBase5000003Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000004":
        KeywordInfoBase5000004Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000005":
        KeywordInfoBase5000005Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000006":
        KeywordInfoBase5000006Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000007":
        KeywordInfoBase5000007Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000008":
        KeywordInfoBase5000008Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
      case "50000009":
        KeywordInfoBase5000009Repository.updateIsSeasonByKeyword(isSeason, keywordResult);
        break;
    }
  }

  public List<String> getKeywords(String category) {
    List<String> result = new ArrayList<>();
    switch (category) {
      case "50000000":
        result = KeywordInfoBase5000000Repository.getKeyword();
        break;
      case "50000001":
        result = KeywordInfoBase5000001Repository.getKeyword();
        break;
      case "50000002":
        result = KeywordInfoBase5000002Repository.getKeyword();
        break;
      case "50000003":
        result = KeywordInfoBase5000003Repository.getKeyword();
        break;
      case "50000004":
        result = KeywordInfoBase5000004Repository.getKeyword();
        break;
      case "50000005":
        result = KeywordInfoBase5000005Repository.getKeyword();
        break;
      case "50000006":
        result = KeywordInfoBase5000006Repository.getKeyword();
        break;
      case "50000007":
        result = KeywordInfoBase5000007Repository.getKeyword();
        break;
      case "50000008":
        result = KeywordInfoBase5000008Repository.getKeyword();
        break;
      case "50000009":
        result = KeywordInfoBase5000009Repository.getKeyword();
        break;
    }
    return result;
  }
}
