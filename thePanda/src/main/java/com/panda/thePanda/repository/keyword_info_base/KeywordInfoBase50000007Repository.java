package com.panda.thePanda.repository.keyword_info_base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000007Entity;

import jakarta.transaction.Transactional;

@Repository
public interface KeywordInfoBase50000007Repository extends JpaRepository<KeywordInfoBaseName50000007Entity, String> {

        @Query("SELECT t.keyword FROM keyword_info_base_50000007 t")
        List<String> getKeyword();

        @Modifying
        @Transactional
        @Query("UPDATE keyword_info_base_50000007 SET total_product_count = :total_product_count, top_product_link = :top_product_link, brand = :brand, price = :price, product_name = :product_name, update_date = CURDATE() WHERE keyword = :keyword")
        void updateUpdateDate(
                        @Param("total_product_count") String total_product_count,
                        @Param("top_product_link") String top_product_link,
                        @Param("brand") String brand,
                        @Param("price") String price,
                        @Param("product_name") String product_name,
                        @Param("keyword") String keyword);

        @Modifying
        @Transactional
        @Query("INSERT INTO keyword_info_base_50000007 (keyword, total_product_count, top_product_link,category1,category2,category3,category4,brand,price,product_name, is_season, update_date) VALUES (:keyword, :total_product_count, :top_product_link, :category1, :category2,:category3, :category4, :brand, :price, :product_name,:is_season, CURDATE())")
        void saveCategoryData(
                        @Param("keyword") String keyword,
                        @Param("total_product_count") String total_product_count,
                        @Param("top_product_link") String top_product_link,
                        @Param("category1") String category1,
                        @Param("category2") String category2,
                        @Param("category3") String category3,
                        @Param("category4") String category4,
                        @Param("brand") String brand,
                        @Param("price") String price,
                        @Param("product_name") String product_name,
                        @Param("is_season") String is_season);

        @Modifying
        @Transactional
        @Query("UPDATE keyword_info_base_50000007 SET is_season = :is_season WHERE keyword = :keyword")
        void updateIsSeasonByKeyword(
                        @Param("is_season") String is_season,
                        @Param("keyword") String keyword);

}
