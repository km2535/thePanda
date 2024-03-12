package com.panda.thePanda.entity.keyword_info_base;

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
@Entity(name = "keyword_info_base_50000008")
@Table(name = "keyword_info_base_50000008")
public class KeywordInfoBaseName50000008Entity {

  @Id
  @Column(name = "keyword")
  private String keyword;
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
  @Nullable
  @Column(name = "update_date")
  private Date update_date;

}
