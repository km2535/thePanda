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
@Entity(name = "keyword_info_custom")
public class KeywordInfoCustomEntity {

  @Id
  @Column(name = "keyword")
  private String keyword;
  @Column(name = "update_date")
  private String update_date;
  @Column(name = "createdate")
  private String createdate;
  @Column(name = "total_product_count")
  private String total_product_count;
  @Column(name = "top_product_link")
  private String top_product_link;
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
  @Column(name = "product_name")
  private String product_name;
  @Column(name = "is_season")
  private String is_season;
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
}