package com.panda.thePanda.service.get_keyword;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panda.thePanda.entity.keyword_info.KeywordInfo50000000Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000001Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000002Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000003Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000004Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000005Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000006Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000007Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000008Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000009Entity;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000000Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000001Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000002Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000003Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000004Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000005Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000006Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000007Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000008Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000009Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetKeywordService {
  private final KeywordInfo50000000Repository keywordInfo50000000Repository;
  private final KeywordInfo50000001Repository keywordInfo50000001Repository;
  private final KeywordInfo50000002Repository keywordInfo50000002Repository;
  private final KeywordInfo50000003Repository keywordInfo50000003Repository;
  private final KeywordInfo50000004Repository keywordInfo50000004Repository;
  private final KeywordInfo50000005Repository keywordInfo50000005Repository;
  private final KeywordInfo50000006Repository keywordInfo50000006Repository;
  private final KeywordInfo50000007Repository keywordInfo50000007Repository;
  private final KeywordInfo50000008Repository keywordInfo50000008Repository;
  private final KeywordInfo50000009Repository keywordInfo50000009Repository;

  public List<KeywordInfo50000000Entity> getKeywordInfoByCategory50000000() {
    return keywordInfo50000000Repository.getKeyword();
  }

  public List<KeywordInfo50000001Entity> getKeywordInfoByCategory50000001() {
    return keywordInfo50000001Repository.getKeyword();
  }

  public List<KeywordInfo50000002Entity> getKeywordInfoByCategory50000002() {
    return keywordInfo50000002Repository.getKeyword();
  }

  public List<KeywordInfo50000003Entity> getKeywordInfoByCategory50000003() {
    return keywordInfo50000003Repository.getKeyword();
  }

  public List<KeywordInfo50000004Entity> getKeywordInfoByCategory50000004() {
    return keywordInfo50000004Repository.getKeyword();
  }

  public List<KeywordInfo50000005Entity> getKeywordInfoByCategory50000005() {
    return keywordInfo50000005Repository.getKeyword();
  }

  public List<KeywordInfo50000006Entity> getKeywordInfoByCategory50000006() {
    return keywordInfo50000006Repository.getKeyword();
  }

  public List<KeywordInfo50000007Entity> getKeywordInfoByCategory50000007() {
    return keywordInfo50000007Repository.getKeyword();
  }

  public List<KeywordInfo50000008Entity> getKeywordInfoByCategory50000008() {
    return keywordInfo50000008Repository.getKeyword();
  }

  public List<KeywordInfo50000009Entity> getKeywordInfoByCategory50000009() {
    return keywordInfo50000009Repository.getKeyword();
  }

}
