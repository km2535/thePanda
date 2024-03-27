package com.panda.thePanda.repository.keyword_save;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_save.KeywordSaveEntity;

@Repository
public interface KeywordSaveRepository extends JpaRepository<KeywordSaveEntity, String> {

  @Query("SELECT k FROM keyword_top k WHERE k.category_id = :category_id ORDER BY rank LIMIT 500")
  List<KeywordSaveEntity> findByCategoryId(Integer category_id);

  @Query("SELECT k FROM keyword_top k ORDER BY category_id, rank")
  List<KeywordSaveEntity> findOrderByCategoryIdAndRank();
}
