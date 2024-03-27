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
@Entity(name = "keyword_result_backup")
@Table(name = "keyword_result_backup")
public class KeywordDetailBackupEntity {
  @Id
  @Column(name = "id")
  private String id;
  @Null
  @Column(name = "keyword", nullable = true)
  private String keyword;
  @Null
  @Column(name = "total_product_count", nullable = true)
  private String total_product_count;
  @Null
  @Column(name = "top_product_link", nullable = true)
  private String top_product_link;
  @Null
  @Column(name = "category1", nullable = true)
  private String category1;
  @Null
  @Column(name = "category2", nullable = true)
  private String category2;
  @Null
  @Column(name = "category3", nullable = true)
  private String category3;
  @Null
  @Column(name = "category4", nullable = true)
  private String category4;
  @Null
  @Column(name = "brand", nullable = true)
  private String brand;
  @Null
  @Column(name = "product_name", nullable = true)
  private String product_name;
  @Null
  @Column(name = "is_season", nullable = true)
  private String is_season;
  @Null
  @Column(name = "monthly_pc_qc_cnt", nullable = true)
  private String monthly_pc_qc_cnt;
  @Null
  @Column(name = "monthly_mobile_qc_cnt", nullable = true)
  private String monthly_mobile_qc_cnt;
  @Null
  @Column(name = "total_qc_cnt", nullable = true)
  private Integer total_qc_cnt;
  @Null
  @Column(name = "monthly_ave_pc_cnt", nullable = true)
  private String monthly_ave_pc_cnt;
  @Null
  @Column(name = "monthly_ave_mobile_cnt", nullable = true)
  private String monthly_ave_mobile_cnt;
  @Null
  @Column(name = "comp_idx", nullable = true)
  private String comp_idx;
  @Null
  @Column(name = "check_top", nullable = true)
  private String check_top;
  @Null
  @Column(name = "ad_cost", nullable = true)
  private Integer ad_cost;
  @Null
  @Column(name = "lprice", nullable = true)
  private Integer lprice;
  @Null
  @Column(name = "hprice", nullable = true)
  private Integer hprice;
  @Null
  @Column(name = "category_id", nullable = true)
  private Integer category_id;
  @Null
  @Column(name = "update_date", nullable = true)
  private String update_date;
  @Null
  @Column(name = "create_date", nullable = true)
  private String create_date;
  @Null
  @Column(name = "is_new", nullable = true)
  private String is_new;
  @Null
  @Column(name = "diff_total_qc_cnt", nullable = true)
  private Integer diff_total_qc_cnt;
}
