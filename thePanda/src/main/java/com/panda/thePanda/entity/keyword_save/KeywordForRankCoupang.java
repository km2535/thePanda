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
@Entity(name = "keyword_for_rank_coupang")
public class KeywordForRankCoupang {
  @Id
  @Column(name = "productId")
  private String productId;
  @Column(name = "vendorId")
  private String vendorId;
  @Column(name = "productName")
  private String productName;
  @Column(name = "image")
  private String image;
  @Column(name = "link")
  private String link;
  @Column(name = "originalPrice")
  private String originalPrice;
  @Column(name = "salePrice")
  private String salePrice;
  @Column(name = "rating")
  private String rating;
  @Column(name = "rewqardInfo")
  private String rewqardInfo;
  @Column(name = "ranking")
  private String ranking;
  @Column(name = "reviewCount")
  private String reviewCount;
  @Column(name = "deliveryInfo")
  private String deliveryInfo;
  @Column(name = "rocketImgUrl")
  private String rocketImgUrl;
  @Column(name = "rocket")
  private String rocket;
  @Column(name = "isAd")
  private String isAd;
  @Column(name = "sortType")
  private String sortType;
}
