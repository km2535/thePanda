package com.panda.thePanda.service.keyword_detail;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panda.thePanda.entity.keyword_save.KeywordDetailEntity;
import com.panda.thePanda.repository.keyword_save.KeywordDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordDetailGetDataService {
  private final KeywordDetailRepository detailRepository;

  public List<KeywordDetailEntity> getKeywordDetail(String keyword) {
    return detailRepository.findByKeyword(keyword);
  }
}
