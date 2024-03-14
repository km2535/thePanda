package com.panda.thePanda.entity.keyword_info_base_name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "keyword_info_custom_by_rising")
public class KeywordInfoCustomByRisingEntity {

  @Id
  @Column(name = "keyword")
  private String keyword;
  @Column(name = "total_qc_cnt_recent")
  private String total_qc_cnt_recent;
  @Column(name = "total_qc_cnt_previous")
  private String total_qc_cnt_previous;
  @Column(name = "category1")
  private String category1;
  @Column(name = "category2")
  private String category2;
  @Column(name = "category3")
  private String category3;
  @Column(name = "category4")
  private String category4;
  @Column(name = "brand")
  private String brand;
  @Column(name = "price")
  private String price;
  @Column(name = "is_season")
  private String is_season;
  @Column(name = "total_product_count")
  private String total_product_count;
  @Column(name = "monthly_pc_qc_cnt")
  private String monthly_pc_qc_cnt;
  @Column(name = "monthly_mobile_qc_cnt")
  private String monthly_mobile_qc_cnt;
  @Column(name = "comp_idx")
  private String comp_idx;
  @Column(name = "total_qc_cnt_difference")
  private String total_qc_cnt_difference;
  @Column(name = "total_qc_cnt_difference_by_percent")
  private String total_qc_cnt_difference_by_percent;
}