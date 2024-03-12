package com.panda.thePanda.service.get_keyword;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000000Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000001Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000002Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000003Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000004Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000005Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000006Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000007Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000008Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000009Entity;
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
public class GetCategoryService {
  private final KeywordInfoBase50000000Repository base50000000Repository;
  private final KeywordInfoBase50000001Repository base50000001Repository;
  private final KeywordInfoBase50000002Repository base50000002Repository;
  private final KeywordInfoBase50000003Repository base50000003Repository;
  private final KeywordInfoBase50000004Repository base50000004Repository;
  private final KeywordInfoBase50000005Repository base50000005Repository;
  private final KeywordInfoBase50000006Repository base50000006Repository;
  private final KeywordInfoBase50000007Repository base50000007Repository;
  private final KeywordInfoBase50000008Repository base50000008Repository;
  private final KeywordInfoBase50000009Repository base50000009Repository;

  public List<KeywordInfoBaseName50000000Entity> getKeywordInfoByCategory50000000() {
    return base50000000Repository.findAll();
  }

  public List<KeywordInfoBaseName50000001Entity> getKeywordInfoByCategory50000001() {
    return base50000001Repository.findAll();
  }

  public List<KeywordInfoBaseName50000002Entity> getKeywordInfoByCategory50000002() {
    return base50000002Repository.findAll();
  }

  public List<KeywordInfoBaseName50000003Entity> getKeywordInfoByCategory50000003() {
    return base50000003Repository.findAll();
  }

  public List<KeywordInfoBaseName50000004Entity> getKeywordInfoByCategory50000004() {
    return base50000004Repository.findAll();
  }

  public List<KeywordInfoBaseName50000005Entity> getKeywordInfoByCategory50000005() {
    return base50000005Repository.findAll();
  }

  public List<KeywordInfoBaseName50000006Entity> getKeywordInfoByCategory50000006() {
    return base50000006Repository.findAll();
  }

  public List<KeywordInfoBaseName50000007Entity> getKeywordInfoByCategory50000007() {
    return base50000007Repository.findAll();
  }

  public List<KeywordInfoBaseName50000008Entity> getKeywordInfoByCategory50000008() {
    return base50000008Repository.findAll();
  }

  public List<KeywordInfoBaseName50000009Entity> getKeywordInfoByCategory50000009() {
    return base50000009Repository.findAll();
  }
}
