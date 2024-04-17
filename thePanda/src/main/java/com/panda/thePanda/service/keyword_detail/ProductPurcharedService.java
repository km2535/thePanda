package com.panda.thePanda.service.keyword_detail;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.panda.thePanda.dto.ProductInfo;
import com.panda.thePanda.service.crawler.NaverProductNameCrawler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductPurcharedService {
  private final NaverProductNameCrawler productNameCrawler;

  public HashMap<String, Integer> getPurcharsedCount(String keyword) {

    HashMap<String, Integer> result = new HashMap<>();
    List<ProductInfo> purcharsedList = productNameCrawler.getProductByKeyword(keyword);
    System.out.println(purcharsedList);
    if (purcharsedList.isEmpty() || !(purcharsedList.size() > 0)) {
      result.put("totalRevenue", 0);
      result.put("totalSalesCount", 0);
      result.put("averagePrice", 0);
      return result;
    }
    // 총 매출액, 총 판매량 계산
    int totalRevenue = calculateTotalRevenue(purcharsedList);
    int totalSalesCount = calculateTotalSalesCount(purcharsedList);

    // 평균 상품가 계산
    int averagePrice = calculateAveragePrice(totalRevenue, totalSalesCount);

    result.put("totalRevenue", totalRevenue);
    result.put("totalSalesCount", totalSalesCount);
    result.put("averagePrice", averagePrice);

    return result;
  }

  // 총 매출액 계산 메서드
  private static int calculateTotalRevenue(List<ProductInfo> salesItems) {
    int totalRevenue = 0;
    for (ProductInfo item : salesItems) {
      if (item.getPurchaseCount().equals("999+")) {
        item.setPurchaseCount("1000");
      }
      totalRevenue += (Integer.parseInt(item.getPrice().replace(",", "")) * Integer
          .parseInt(item.getPurchaseCount().replace(",", "")));
    }
    return totalRevenue;
  }

  // 총 판매량 계산 메서드
  private static int calculateTotalSalesCount(List<ProductInfo> salesItems) {
    int totalSalesCount = 0;
    for (ProductInfo item : salesItems) {
      if (item.getPurchaseCount().equals("999+")) {
        item.setPurchaseCount("1000");
      }
      totalSalesCount += Integer.parseInt(item.getPurchaseCount());
    }
    return totalSalesCount;
  }

  // 평균 상품가 계산 메서드
  private static int calculateAveragePrice(int totalRevenue, int totalSalesCount) {
    if (totalRevenue == 0 || totalSalesCount == 0)
      return 0;
    return (int) totalRevenue / totalSalesCount;
  }
}
