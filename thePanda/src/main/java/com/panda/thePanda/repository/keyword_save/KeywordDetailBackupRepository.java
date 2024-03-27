package com.panda.thePanda.repository.keyword_save;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_save.KeywordDetailBackupEntity;

import jakarta.transaction.Transactional;

@Repository
public interface KeywordDetailBackupRepository extends JpaRepository<KeywordDetailBackupEntity, String> {
        @Modifying
        @Transactional
        @Query("UPDATE keyword_result_backup " +
                        "SET keyword = :keyword, " +
                        "total_product_count = :total_product_count, " +
                        "top_product_link = :top_product_link, " +
                        "category1 = :category1, " +
                        "category2 = :category2, " +
                        "category3 = :category3, " +
                        "category4 = :category4, " +
                        "brand = :brand, " +
                        "product_name = :product_name, " +
                        "is_season = :is_season, " +
                        "monthly_pc_qc_cnt = :monthly_pc_qc_cnt, " +
                        "monthly_mobile_qc_cnt = :monthly_mobile_qc_cnt, " +
                        "total_qc_cnt = :total_qc_cnt, " +
                        "monthly_ave_pc_cnt = :monthly_ave_pc_cnt, " +
                        "monthly_ave_mobile_cnt = :monthly_ave_mobile_cnt, " +
                        "comp_idx = :comp_idx, " +
                        "check_top = :check_top, " +
                        "ad_cost = :ad_cost, " +
                        "lprice = :lprice, " +
                        "hprice = :hprice, " +
                        "category_id = :category_id, " +
                        "update_date = :update_date, " +
                        "create_date = :create_date, " +
                        "is_new = :is_new," +
                        "diff_total_qc_cnt = :diff_total_qc_cnt " +
                        "WHERE id = :id")
        void updateDataByKeyword(
                        @Param("id") String id,
                        @Param("keyword") String keyword,
                        @Param("total_product_count") String total_product_count,
                        @Param("top_product_link") String top_product_link,
                        @Param("category1") String category1,
                        @Param("category2") String category2,
                        @Param("category3") String category3,
                        @Param("category4") String category4,
                        @Param("brand") String brand,
                        @Param("product_name") String product_name,
                        @Param("is_season") String is_season,
                        @Param("monthly_pc_qc_cnt") String monthly_pc_qc_cnt,
                        @Param("monthly_mobile_qc_cnt") String monthly_mobile_qc_cnt,
                        @Param("total_qc_cnt") Integer integer,
                        @Param("monthly_ave_pc_cnt") String monthly_ave_pc_cnt,
                        @Param("monthly_ave_mobile_cnt") String monthly_ave_mobile_cnt,
                        @Param("comp_idx") String comp_idx,
                        @Param("check_top") String check_top,
                        @Param("ad_cost") Integer ad_cost,
                        @Param("lprice") Integer lprice,
                        @Param("hprice") Integer hprice,
                        @Param("category_id") Integer category_id,
                        @Param("update_date") String update_date,
                        @Param("create_date") String create_date,
                        @Param("is_new") String is_new,
                        @Param("diff_total_qc_cnt") Integer diff_total_qc_cnt);

        @Query("SELECT k FROM keyword_result_backup k WHERE k.keyword = :keyword AND k.category_id = :category_id ORDER BY total_qc_cnt DESC LIMIT 500")
        KeywordDetailBackupEntity getByKeyword(
                        @Param("keyword") String keyword,
                        @Param("category_id") Integer category_id);

        List<KeywordDetailBackupEntity> findByKeyword(String keyword);

        @Query("SELECT k FROM keyword_result_backup k WHERE k.check_top = 'true' ORDER BY total_qc_cnt DESC LIMIT 500")
        List<KeywordDetailBackupEntity> findByCheckTop();

        @Query("SELECT k FROM keyword_result_backup k WHERE k.check_top = 'true' ORDER BY diff_total_qc_cnt DESC, total_qc_cnt DESC LIMIT 500")
        List<KeywordDetailBackupEntity> findByCheckTopSortByDiffTotalQcCnt();

        @Query("SELECT k FROM keyword_result_backup k WHERE k.check_top = 'true' ORDER BY is_new DESC, total_qc_cnt DESC LIMIT 500")
        List<KeywordDetailBackupEntity> findByCheckTopSortByIsNew();

        @Query("SELECT k FROM keyword_result_backup k WHERE k.check_top = 'true' AND brand = '' ORDER BY total_qc_cnt DESC LIMIT 500")
        List<KeywordDetailBackupEntity> findByCheckTopNotBrand();

        @Query("SELECT k FROM keyword_result_backup k WHERE k.check_top = 'true' AND brand = '' ORDER BY diff_total_qc_cnt DESC, total_qc_cnt DESC LIMIT 500")
        List<KeywordDetailBackupEntity> findByCheckTopNotBrandSortByDiffTotalQcCnt();

        @Query("SELECT k FROM keyword_result_backup k WHERE k.check_top = 'true' AND brand = '' ORDER BY is_new DESC,  total_qc_cnt DESC LIMIT 500")
        List<KeywordDetailBackupEntity> findByCheckTopNotBrandSortByIsNew();

        @Query("SELECT k FROM keyword_result_backup k WHERE k.category_id = :category_id AND k.check_top = 'true' ORDER BY total_qc_cnt DESC")
        List<KeywordDetailBackupEntity> findByCheckTopCategoryId(@Param("category_id") Integer categoryId);

        @Query("SELECT k FROM keyword_result_backup k WHERE k.category_id = :category_id AND k.check_top = 'true' ORDER BY diff_total_qc_cnt DESC, total_qc_cnt DESC")
        List<KeywordDetailBackupEntity> findByCheckTopCategoryIdSortByDiffTotalQc(
                        @Param("category_id") Integer categoryId);

        @Query("SELECT k FROM keyword_result_backup k WHERE k.category_id = :category_id AND k.check_top = 'true' ORDER BY is_new DESC, total_qc_cnt DESC")
        List<KeywordDetailBackupEntity> findByCheckTopCategoryIdSortByIsNew(
                        @Param("category_id") Integer categoryId);

        @Query("SELECT k FROM keyword_result_backup k WHERE k.category_id = :category_id AND brand = '' AND k.check_top = 'true' ORDER BY total_qc_cnt DESC")
        List<KeywordDetailBackupEntity> findByCheckTopCategoryIdAndNotBrand(@Param("category_id") Integer categoryId);

        @Query("SELECT k FROM keyword_result_backup k WHERE k.category_id = :category_id AND brand = '' AND k.check_top = 'true' ORDER BY diff_total_qc_cnt DESC, total_qc_cnt DESC")
        List<KeywordDetailBackupEntity> findByCheckTopCategoryIdAndNotBrandSortByDiffTotalQcCnt(
                        @Param("category_id") Integer categoryId);

        @Query("SELECT k FROM keyword_result_backup k WHERE k.category_id = :category_id AND brand = '' AND k.check_top = 'true' ORDER BY is_new DESC, total_qc_cnt DESC")
        List<KeywordDetailBackupEntity> findByCheckTopCategoryIdAndNotBrandSortByIsNew(
                        @Param("category_id") Integer categoryId);

}
