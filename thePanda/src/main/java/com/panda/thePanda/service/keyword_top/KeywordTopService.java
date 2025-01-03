package com.panda.thePanda.service.keyword_top;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.panda.thePanda.entity.keyword_save.KeywordSaveEntity;
import com.panda.thePanda.repository.keyword_save.KeywordSaveRepository;
import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordTopService {
  private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;
  private final KeywordSaveRepository keywordSaveRepository;
  private final KeywordSaveRepository saveRepository;

  public void deleteAllData() {
    keywordSaveRepository.deleteAllInBatch();
  }

  public List<String> searchAndSave(Integer category) {
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedcurrentDate = currentDate.format(formatter);

    List<String> keywords = new ArrayList<>();

    for (int j = 1; j <= 25; j++) {
      keywords.addAll(dataLabTopKeywordCrawler.crawlTopKeywordByCategory(category, j));
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    // 모든 데이터를 저장한다.
    List<KeywordSaveEntity> entities = new ArrayList<>();
    for (int i = 0; i < keywords.size(); i++) {
      KeywordSaveEntity entity = new KeywordSaveEntity();
      String keyword = keywords.get(i);
      entity.setId(keyword + category);
      entity.setKeyword(keyword);
      entity.setCategory_id(category);
      entity.setRank(i + 1);
      entity.setCreate_date(formattedcurrentDate);
      entities.add(entity);
    }
    keywordSaveRepository.saveAll(entities);
    return keywords;
  }

  public List<KeywordSaveEntity> getTopKeyword(Integer category_id) {
    List<KeywordSaveEntity> result = saveRepository.findByCategoryId(category_id);
    return result;
  }

  public List<KeywordSaveEntity> getAllTopKeyword() {
    List<KeywordSaveEntity> result = saveRepository.findOrderByCategoryIdAndRank();
    return result;
  }

}
