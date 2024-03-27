package com.panda.thePanda.service.keyword_detail;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panda.thePanda.entity.keyword_save.KeywordDetailBackupEntity;
import com.panda.thePanda.repository.keyword_save.KeywordDetailBackupRepository;
import com.panda.thePanda.service.keyword_top.KeywordTopService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordDetailGetDataService {
  private final KeywordDetailBackupRepository backupRepository;
  private final KeywordDetailBackupService detailBackupService;
  private final KeywordTopService keywordSaveService;
  private final List<KeywordDetailBackupEntity> entities;

  public List<KeywordDetailBackupEntity> getKeywordDetail(String keyword) {
    return backupRepository.findByKeyword(keyword);
  }

  // 모든 데이터를 검색하여 가져옴
  public List<KeywordDetailBackupEntity> getAllKeywordDetail() {
    return backupRepository.findByCheckTop();
  }

  // 모든 데이터를 검색하여 상승이 큰 순으로 정렬하여 가져옴
  public List<KeywordDetailBackupEntity> getAllKeywordDetailSortByDiffTotalQcCnt() {
    return backupRepository.findByCheckTopSortByDiffTotalQcCnt();
  }

  // 모든 데이터를 검색하여 새로운 키워드 순으로 정렬하여 가져옴
  public List<KeywordDetailBackupEntity> getAllKeywordDetailSortByIsNew() {
    return backupRepository.findByCheckTopSortByIsNew();
  }

  // 브랜드가 아닌 모든 데이터를 검색하여 가져옴
  public List<KeywordDetailBackupEntity> getAllKeywordDetailNotBrand() {
    return backupRepository.findByCheckTopNotBrand();
  }

  // 브랜드가 아닌 모든 데이터를 검색하여 상승이 큰 순으로 가져옴
  public List<KeywordDetailBackupEntity> getAllKeywordDetailNotBrandSortByDiffTotalQcCnt() {
    return backupRepository.findByCheckTopNotBrandSortByDiffTotalQcCnt();
  }

  // 브랜드가 아닌 모든 데이터를 검색하여 새로운 키워드 순으로 가져옴
  public List<KeywordDetailBackupEntity> getAllKeywordDetailNotBrandSortByIsNew() {
    return backupRepository.findByCheckTopNotBrandSortByIsNew();
  }

  // 브랜드가 아닌 특정 카테고리의 데이터를 검색하여 가져옴
  public List<KeywordDetailBackupEntity> getKeywordDetailNotBrand(int categoryId) {
    return backupRepository.findByCheckTopCategoryIdAndNotBrand(categoryId);
  }

  // 브랜드가 아닌 특정 카테고리의 데이터를 상승이 큰 데이터로 정렬
  public List<KeywordDetailBackupEntity> getKeywordDetailNotBrandSortByDiffTotalQcCnt(int categoryId) {
    return backupRepository.findByCheckTopCategoryIdAndNotBrandSortByDiffTotalQcCnt(categoryId);
  }

  // 브랜드가 아닌 특정 카테고리의 데이터를 새로운 키워드 순으로 정렬
  public List<KeywordDetailBackupEntity> getKeywordDetailNotBrandSortByIsNew(int categoryId) {
    return backupRepository.findByCheckTopCategoryIdAndNotBrandSortByIsNew(categoryId);
  }

  // 특정 카테고리에서 가져옴
  public List<KeywordDetailBackupEntity> getTopKeywordDetail(Integer categoryId) {
    return backupRepository.findByCheckTopCategoryId(categoryId);
  }

  // 특정 카테고리와 가장 상승이 큰 데이터로 정렬
  public List<KeywordDetailBackupEntity> getTopKeywordDetailSortByDiffTotalQcCnt(Integer categoryId) {
    return backupRepository.findByCheckTopCategoryIdSortByDiffTotalQc(categoryId);
  }

  // 특정 카테고리와 새로운 키워드 순으로 정렬
  public List<KeywordDetailBackupEntity> getTopKeywordDetailSortByIsNew(Integer categoryId) {
    return backupRepository.findByCheckTopCategoryIdSortByIsNew(categoryId);
  }

}
