package com.panda.thePanda.entity.keyword_save;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "keyword_result")
@Table(name = "keyword_result")
public class KeywordDetailEntity {
  @Id
  @Column(name = "id")
  private String id;
  @Null
  @Column(name = "keyword", nullable = true)
  private String keyword;
  @Column(name = "total_product_count", nullable = true)
  private String total_product_count;
  @Column(name = "top_product_link", nullable = true)
  private String top_product_link;
  @Column(name = "category1", nullable = true)
  private String category1;
  @Column(name = "category2", nullable = true)
  private String category2;
  @Column(name = "category3", nullable = true)
  private String category3;
  @Column(name = "category4", nullable = true)
  private String category4;
  @Column(name = "brand", nullable = true)
  private String brand;
  @Column(name = "product_name", nullable = true)
  private String product_name;
  @Column(name = "is_season", nullable = true)
  private String is_season;
  @Column(name = "monthly_pc_qc_cnt", nullable = true)
  private String monthly_pc_qc_cnt;
  @Column(name = "monthly_mobile_qc_cnt", nullable = true)
  private String monthly_mobile_qc_cnt;
  @Column(name = "total_qc_cnt", nullable = true)
  private String total_qc_cnt;
  @Column(name = "monthly_ave_pc_cnt", nullable = true)
  private String monthly_ave_pc_cnt;
  @Column(name = "monthly_ave_mobile_cnt", nullable = true)
  private String monthly_ave_mobile_cnt;
  @Column(name = "comp_idx", nullable = true)
  private String comp_idx;
  @Column(name = "is_top", nullable = true)
  private String is_top;
  @Column(name = "ad_cost", nullable = true)
  private int ad_cost;
  @Column(name = "lprice", nullable = true)
  private int lprice;
  @Column(name = "hprice", nullable = true)
  private int hprice;
  @Column(name = "category_id", nullable = true)
  private String category_id;
  @Column(name = "update_date", nullable = true)
  private String update_date;
  @Column(name = "create_date", nullable = true)
  private String create_date;
}
