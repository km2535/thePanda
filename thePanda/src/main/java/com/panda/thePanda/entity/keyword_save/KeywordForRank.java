package com.panda.thePanda.entity.keyword_save;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "keyword_for_rank")
public class KeywordForRank {
  @Id
  @Column(name = "productId")
  private String productId;
  @Column(name = "title", nullable = true)
  private String title;
  @Column(name = "link", nullable = true)
  private String link;
  @Column(name = "image", nullable = true)
  private String image;
  @Column(name = "lprice", nullable = true)
  private Integer lprice;
  @Column(name = "hprice", nullable = true)
  private Integer hprice;
  @Column(name = "mallName", nullable = true)
  private String mallName;
  @Column(name = "category1", nullable = true)
  private String category1;
  @Column(name = "category2", nullable = true)
  private String category2;
  @Column(name = "category3", nullable = true)
  private String category3;
  @Column(name = "category4", nullable = true)
  private String category4;
  @Column(name = "productType", nullable = true)
  private String productType;
  @Column(name = "maker", nullable = true)
  private String maker;
  @Column(name = "brand", nullable = true)
  private String brand;
  @Column(name = "keyword", nullable = true)
  private String keyword;
  @Column(name = "rank", nullable = true)
  private Integer rank;
}
