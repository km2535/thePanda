package com.panda.thePanda.service.keyword_top;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.panda.thePanda.entity.keyword_save.KeywordForRankCoupang;

@Service
public class KeywordRankTrackingCoupang {
  public HashMap<String, KeywordForRankCoupang> getRankByCoupangProductList(String keywords, String productNumber,
      String sorted)
      throws IOException {
    HashMap<String, KeywordForRankCoupang> productList = new HashMap<>();
    String[] keywordArr = keywords.split(",");
    for (String keyword : keywordArr) {
      KeywordForRankCoupang product = getRankProduct(productList, keyword, productNumber, "1", sorted);
      System.out.println(product.getProductName());
      if (product.getProductId().equals("")) {
        getRankProduct(productList, keyword, productNumber, "2", sorted);
      }
    }
    return productList;
  }

  private void setInitData(KeywordForRankCoupang product) {
    product.setProductName("");
    product.setImage("");
    product.setLink("");
    product.setOriginalPrice("");
    product.setSalePrice("");
    product.setRating("");
    product.setReviewCount("");
    product.setRewqardInfo("");
    product.setProductId("");
    product.setIsAd("");
    product.setVendorId("");
    product.setRanking("");
    product.setDeliveryInfo("");
    product.setRocketImgUrl("");
    product.setRocket("");
    product.setSortType("");
  }

  private KeywordForRankCoupang getRankProduct(HashMap<String, KeywordForRankCoupang> productList,
      String keyword, String productNumber, String page, String sorted) throws IOException {
    // 키워드, 카테고리와 페이지 수로 검색을 하고
    String url = "https://www.coupang.com/np/search?q=" + URLEncoder.encode(keyword, "UTF-8")
        + "&filterSetByUser=true&channel=user&rocketAll=false&sorter=" + sorted
        + "&isPriceRange=false&listSize=100&component=&page=" + page + "&rating=0";
    // Connection 객체 생성 및 헤더 설정
    Connection connection = Jsoup.connect(url)
        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
            + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
        .header("Accept-Language", "UTF-8");
    // HTTP 요청 보내기
    Document doc = connection.get();
    // 상품 리스트 가져오기
    Elements products = doc.select("li.search-product");
    // System.out.println(doc);
    // 리뷰가 존재하는 상품만 정보를 map에 담아서
    // 리스트에 추가한다.
    KeywordForRankCoupang product = new KeywordForRankCoupang();
    for (int j = 0; j < products.size(); j++) {
      Element item = products.get(j);
      String productName = item.select("div.name").text();
      String originalPrice = item.select("del.base-price").text();
      String salePrice = item.select("strong.price-value").text();
      String rating = item.select("em.rating").text();
      String reviewCount = item.select("span.rating-total-count").text();
      String rewardInfo = item.select("span.reward-cash-txt").text();
      String deliveryInfo = item.select("span.arrival-info").text();
      String productId = item.attr("data-product-id");
      String vendorId = item.attr("data-vendor-item-id");
      String rocket = item.select("span.badge.rocket").select("img").attr("src");
      String image = item.select("img.search-product-wrap-img").attr("src");
      String link = item.select(".search-product-link").attr("href");
      String ad = item.select("span.ad-badge-text").text().equals("AD") ? "true" : "false";

      if (productId.equals(productNumber)) {
        // 일치하는 상품 확인
        product = new KeywordForRankCoupang();
        product.setProductName(productName);
        product.setImage("https:" + image);
        product.setLink("https://www.coupang.com" + link);
        product.setOriginalPrice(originalPrice);
        product.setSalePrice(salePrice);
        product.setRating(rating);
        product.setReviewCount(reviewCount);
        product.setRewqardInfo(rewardInfo);
        product.setProductId(productId);
        product.setIsAd(ad);
        product.setVendorId(vendorId);
        product.setSortType(sorted);
        product.setRanking(Integer.parseInt(page) == 1 ? j + "" : (j + 100) + "");
        if (deliveryInfo.equals("")) {
          product.setDeliveryInfo("쿠팡 윙");
        } else if (!deliveryInfo.equals("")) {
          product.setDeliveryInfo(deliveryInfo);
        }
        if (rocket.equals("")) {
          product.setRocketImgUrl("");
          product.setRocket("일반");
        } else if (!rocket.equals("")) {
          product.setRocketImgUrl(rocket);
          product.setRocket("로켓");
        }
        productList.put(keyword, product);
        break;
      } else {
        // 순위 내 없음 또는 일치하는 상품 없
        setInitData(product);
        productList.put(keyword, product);
      }

    }
    return product;
  }
}
