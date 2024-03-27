package com.panda.thePanda.repository.keyword_save;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_save.KeywordByProduct;

@Repository
public interface KeywordByProductsRepository extends JpaRepository<KeywordByProduct, String> {

  @Query("SELECT k FROM keyword_by_products k WHERE k.keyword = :keyword ORDER BY rank")
  List<KeywordByProduct> findByKeyword(@Param(value = "keyword") String keyword);

}
