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
@Entity(name = "keyword_info_custom_by_new")
public class KeywordInfoCustomByNewEntity {

  @Id
  @Column(name = "keyword")
  private String keyword;
  @Column(name = "update_date")
  private String update_date;
  @Column(name = "total_qc_cnt")
  private String total_qc_cnt;
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
  @Column(name = "new_keyword")
  private String new_keyword;
}