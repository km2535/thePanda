package com.panda.thePanda.repository.keyword_info_base_name;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.panda.thePanda.entity.keyword_info_base_name.KeywordInfoCustomByRisingEntity;

public interface KeywordInfoCustomByRisingRepository extends JpaRepository<KeywordInfoCustomByRisingEntity, String> {

  @Query(value = "SELECT b1.keyword, b1.total_qc_cnt AS total_qc_cnt_recent, b2.total_qc_cnt AS total_qc_cnt_previous, k.category1,k.category2,k.category3,k.category4,k.brand,k.price, k.is_season,k.total_product_count, b1.monthly_pc_qc_cnt, b1.monthly_mobile_qc_cnt,b1.comp_idx, b1.total_qc_cnt - b2.total_qc_cnt AS total_qc_cnt_difference, ROUND(((b1.total_qc_cnt - b2.total_qc_cnt) / b2.total_qc_cnt) * 100 ,2)AS total_qc_cnt_difference_by_percent FROM keyword_info_base_50000000 k JOIN keyword_info_50000000_backup b1 ON k.keyword = b1.keyword LEFT JOIN keyword_info_50000000_backup b2 ON b1.keyword = b2.keyword WHERE b1.backup_date = (SELECT MAX(backup_date) FROM keyword_info_50000000_backup ) AND b2.backup_date = (SELECT MAX(backup_date) FROM keyword_info_50000000_backup WHERE backup_date < (SELECT MAX(backup_date) FROM keyword_info_50000000_backup)) AND b1.keyword IN (SELECT keyword FROM keyword_info_50000000_backup GROUP BY keyword HAVING COUNT(*) > 1)", nativeQuery = true)
  public List<KeywordInfoCustomByRisingEntity> get50000000RisingKeyword();

}
