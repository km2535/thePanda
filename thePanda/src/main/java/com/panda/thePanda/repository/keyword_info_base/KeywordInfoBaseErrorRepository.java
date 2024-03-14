package com.panda.thePanda.repository.keyword_info_base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseNameErrorEntity;

import jakarta.transaction.Transactional;

@Repository
public interface KeywordInfoBaseErrorRepository extends JpaRepository<KeywordInfoBaseNameErrorEntity, String> {

        @Query("SELECT t.keyword FROM keyword_info_base_error t WHERE category = :category")
        List<String> getErrorKeyword(@Param("category") String category);

        @Modifying
        @Transactional
        @Query("INSERT INTO keyword_info_base_error (keyword, category, update_date) VALUES (:keyword, :category, CURDATE())")
        void saveErrorData(
                        @Param("keyword") String keyword,
                        @Param("category") String category);

}
