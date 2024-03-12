package com.panda.thePanda.repository.keyword_name;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.panda.thePanda.entity.keyword_name.KeywordName50000009Entity;

import jakarta.transaction.Transactional;

@Repository
public interface KeywordName50000009Repository extends JpaRepository<KeywordName50000009Entity, String> {
  @Query("SELECT t.keyword FROM keyword_name_50000009 t")
  List<String> getKeyword();

  @Modifying
  @Transactional
  @Query("INSERT INTO keyword_name_50000009 (keyword, updatedate, createdate) VALUES (:keyword, CURDATE(), CURDATE())")
  void saveKeyword(@Param("keyword") String keyword);

  @Modifying
  @Transactional
  @Query("UPDATE keyword_name_50000009 SET updatedate = CURDATE() WHERE keyword=:keyword")
  void updateUpdateDate(@Param("keyword") String keyword);
}
