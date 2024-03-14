package com.panda.thePanda.service.keyword_name;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.panda.thePanda.repository.keyword_name.KeywordName50000000Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000001Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000002Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000003Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000004Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000005Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000006Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000007Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000008Repository;
import com.panda.thePanda.repository.keyword_name.KeywordName50000009Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// 데이터 랩 관련 데이터 레포지토리
public class KeywordNameMethod {
  private final KeywordName50000000Repository keyword5000000Repositoty;
  private final KeywordName50000001Repository keyword5000001Repositoty;
  private final KeywordName50000002Repository keyword5000002Repositoty;
  private final KeywordName50000003Repository keyword5000003Repositoty;
  private final KeywordName50000004Repository keyword5000004Repositoty;
  private final KeywordName50000005Repository keyword5000005Repositoty;
  private final KeywordName50000006Repository keyword5000006Repositoty;
  private final KeywordName50000007Repository keyword5000007Repositoty;
  private final KeywordName50000008Repository keyword5000008Repositoty;
  private final KeywordName50000009Repository keyword5000009Repositoty;

  public List<String> keywordGetDatalab(String category) {
    List<String> result = new ArrayList<>();
    switch (category) {
      case "50000000":
        result = keyword5000000Repositoty.getKeyword();
        break;
      case "50000001":
        result = keyword5000001Repositoty.getKeyword();
        break;
      case "50000002":
        result = keyword5000002Repositoty.getKeyword();
        break;
      case "50000003":
        result = keyword5000003Repositoty.getKeyword();
        break;
      case "50000004":
        result = keyword5000004Repositoty.getKeyword();
        break;
      case "50000005":
        result = keyword5000005Repositoty.getKeyword();
        break;
      case "50000006":
        result = keyword5000006Repositoty.getKeyword();
        break;
      case "50000007":
        result = keyword5000007Repositoty.getKeyword();
        break;
      case "50000008":
        result = keyword5000008Repositoty.getKeyword();
        break;
      case "50000009":
        result = keyword5000009Repositoty.getKeyword();
        break;
    }
    return result;
  }

  public void keywordUpdateDatalab(String category, String keyword) {
    switch (category) {
      case "50000000":
        keyword5000000Repositoty.updateUpdateDate(keyword);
        break;
      case "50000001":
        keyword5000001Repositoty.updateUpdateDate(keyword);
        break;
      case "50000002":
        keyword5000002Repositoty.updateUpdateDate(keyword);
        break;
      case "50000003":
        keyword5000003Repositoty.updateUpdateDate(keyword);
        break;
      case "50000004":
        keyword5000004Repositoty.updateUpdateDate(keyword);
        break;
      case "50000005":
        keyword5000005Repositoty.updateUpdateDate(keyword);
        break;
      case "50000006":
        keyword5000006Repositoty.updateUpdateDate(keyword);
        break;
      case "50000007":
        keyword5000007Repositoty.updateUpdateDate(keyword);
        break;
      case "50000008":
        keyword5000008Repositoty.updateUpdateDate(keyword);
        break;
      case "50000009":
        keyword5000009Repositoty.updateUpdateDate(keyword);
        break;
    }
  }

  public void saveKeywordDatalab(String category, String keyword) {
    switch (category) {
      case "50000000":
        keyword5000000Repositoty.saveKeyword(keyword);
        break;
      case "50000001":
        keyword5000001Repositoty.saveKeyword(keyword);
        break;
      case "50000002":
        keyword5000002Repositoty.saveKeyword(keyword);
        break;
      case "50000003":
        keyword5000003Repositoty.saveKeyword(keyword);
        break;
      case "50000004":
        keyword5000004Repositoty.saveKeyword(keyword);
        break;
      case "50000005":
        keyword5000005Repositoty.saveKeyword(keyword);
        break;
      case "50000006":
        keyword5000006Repositoty.saveKeyword(keyword);
        break;
      case "50000007":
        keyword5000007Repositoty.saveKeyword(keyword);
        break;
      case "50000008":
        keyword5000008Repositoty.saveKeyword(keyword);
        break;
      case "50000009":
        keyword5000009Repositoty.saveKeyword(keyword);
        break;
    }
  }

  public void deleteKeywordDatalab(String category, String keyword) {
    switch (category) {
      case "50000000":
        keyword5000000Repositoty.deleteAll();
        break;
      case "50000001":
        keyword5000001Repositoty.deleteAll();
        break;
      case "50000002":
        keyword5000002Repositoty.deleteAll();
        break;
      case "50000003":
        keyword5000003Repositoty.deleteAll();
        break;
      case "50000004":
        keyword5000004Repositoty.deleteAll();
        break;
      case "50000005":
        keyword5000005Repositoty.deleteAll();
        break;
      case "50000006":
        keyword5000006Repositoty.deleteAll();
        break;
      case "50000007":
        keyword5000007Repositoty.deleteAll();
        break;
      case "50000008":
        keyword5000008Repositoty.deleteAll();
        break;
      case "50000009":
        keyword5000009Repositoty.deleteAll();
        break;
    }
  }
}
