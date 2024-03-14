package com.panda.thePanda.repository.keyword_info;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_info.KeywordInfo50000002Entity;

import jakarta.transaction.Transactional;

@Repository
public interface KeywordInfo50000002Repository extends JpaRepository<KeywordInfo50000002Entity, String> {
    @Query(value = "SELECT * FROM keyword_info_50000002 ORDER BY CAST(total_qc_cnt AS SIGNED) DESC", nativeQuery = true)
    List<KeywordInfo50000002Entity> getKeyword();

    @Modifying
    @Transactional
    @Query("INSERT INTO keyword_info_50000002 (keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,total_qc_cnt,monthly_ave_pc_cnt,monthly_ave_mobile_cnt,comp_idx,update_date) VALUES (:keyword, :monthly_pc_qc_cnt, :monthly_mobile_qc_cnt, :total_qc_cnt, :monthly_ave_pc_cnt,:monthly_ave_mobile_cnt, :comp_idx, CURDATE())")
    void saveInfoCompetition(
            @Param("keyword") String keyword,
            @Param("monthly_pc_qc_cnt") String monthly_pc_qc_cnt,
            @Param("monthly_mobile_qc_cnt") String monthly_mobile_qc_cnt,
            @Param("total_qc_cnt") String total_qc_cnt,
            @Param("monthly_ave_pc_cnt") String monthly_ave_pc_cnt,
            @Param("monthly_ave_mobile_cnt") String monthly_ave_mobile_cnt,
            @Param("comp_idx") String comp_idx);

    @Query(value = "INSERT INTO keyword_info_50000002_backup (id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,total_qc_cnt,monthly_ave_pc_cnt,monthly_ave_mobile_cnt,comp_idx,update_date,backup_date) SELECT id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,total_qc_cnt,monthly_ave_pc_cnt,monthly_ave_mobile_cnt,comp_idx,update_date,backup_date FROM (SELECT CONCAT(keyword, DATE_FORMAT(CURDATE(), '%Y%m%d')) AS id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,total_qc_cnt,monthly_ave_pc_cnt,monthly_ave_mobile_cnt,comp_idx,update_date,CURDATE() AS backup_date FROM keyword_info_50000002) AS temp WHERE NOT EXISTS (SELECT 1 FROM keyword_info_50000002_backup WHERE id = temp.id)", nativeQuery = true)
    void backupToBackupTable();

}
