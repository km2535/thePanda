package com.panda.thePanda.entity.keyword_info;

import java.sql.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "keyword_info_50000006")
@Table(name = "keyword_info_50000006")
public class KeywordInfo50000006Entity {

  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "keyword")
  private String keyword;
  @Column(name = "monthly_pc_qc_cnt")
  private String monthly_pc_qc_cnt;
  @Column(name = "monthly_mobile_qc_cnt")
  private String monthly_mobile_qc_cnt;

  @Column(name = "total_qc_cnt")
  private String total_qc_cnt;

  @Column(name = "monthly_ave_pc_cnt")
  private String monthly_ave_pc_cnt;
  @Column(name = "monthly_ave_mobile_cnt")
  private String monthly_ave_mobile_cnt;
  @Column(name = "comp_idx")
  private String comp_idx;
  @Nullable
  @Column(name = "update_date")
  private Date update_date;

}
