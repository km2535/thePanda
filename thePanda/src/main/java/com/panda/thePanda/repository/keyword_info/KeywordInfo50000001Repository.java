package com.panda.thePanda.repository.keyword_info;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_info.KeywordInfo50000001Entity;

import jakarta.transaction.Transactional;

@Repository
public interface KeywordInfo50000001Repository extends JpaRepository<KeywordInfo50000001Entity, String> {

    @Modifying
    @Transactional
    @Query("INSERT INTO keyword_info_50000001 (id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,total_qc_cnt,monthly_ave_pc_cnt,monthly_ave_mobile_cnt,comp_idx,update_date) VALUES (:id, :keyword, :monthly_pc_qc_cnt, :monthly_mobile_qc_cnt, :total_qc_cnt, :monthly_ave_pc_cnt,:monthly_ave_mobile_cnt, :comp_idx, CURDATE())")
    void saveInfoCompetition(
            @Param("id") String id,
            @Param("keyword") String keyword,
            @Param("monthly_pc_qc_cnt") String monthly_pc_qc_cnt,
            @Param("monthly_mobile_qc_cnt") String monthly_mobile_qc_cnt,
            @Param("total_qc_cnt") String total_qc_cnt,
            @Param("monthly_ave_pc_cnt") String monthly_ave_pc_cnt,
            @Param("monthly_ave_mobile_cnt") String monthly_ave_mobile_cnt,
            @Param("comp_idx") String comp_idx);

    @Query(value = "SELECT * FROM keyword_info_50000001 ORDER BY CAST(total_qc_cnt AS SIGNED) DESC", nativeQuery = true)
    List<KeywordInfo50000001Entity> getKeyword();

}
