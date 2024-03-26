package com.panda.thePanda.repository.keyword_save;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_save.KeywordDetailEntity;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface KeywordDetailRepository extends JpaRepository<KeywordDetailEntity, String> {

  @Query("SELECT k FROM keyword_result k WHERE k.check_top = 'true'")
  List<KeywordDetailEntity> findWithCheckTopTrue();

  List<KeywordDetailEntity> findByKeyword(String keyword);

  @Modifying
  @Transactional
  @Query("UPDATE keyword_result SET is_season = :is_season WHERE keyword = :keyword")
  void updateIsSeasonByKeyword(
      @Param("is_season") String is_season,
      @Param("keyword") String keyword);
}
