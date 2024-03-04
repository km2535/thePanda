package com.panda.thePanda.service.crawler;


import jakarta.servlet.http.HttpSession;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RankingFindOnCoupangCrawler {
    @Autowired
    private HttpSession session;

	public Map<String, String> rankingFindByProductId(String pId, String vId, String kWord) throws IOException {
        @SuppressWarnings("unchecked")
        Map<String, String> productDetail = (Map<String, String>) session.getAttribute("productCoupangDetail");
        // 세션을 가져와서 세션이 null 이 아니고 
        if(productDetail != null) {
      
	        if (!productDetail.get("productId").equals(pId)) {
	            // pName과 세션의 productName이 다를 경우 크롤링 실행
	        	
	            productDetail = executeCrawling(pId, vId, kWord);
	        } 

        }else {
            // 세션이 없을 경우 크롤링 실행
        	productDetail = executeCrawling(pId, vId, kWord);
        }
        return productDetail;
    }

    private Map<String, String> executeCrawling(String pId, String vId, String kWord) throws IOException {
    	Map<String, String> productResult = new HashMap<String, String>();
    	int page = 1; 

		 while(productResult.isEmpty() && page <= 10) {
        // 쿠팡 웹 페이지 가져오기
        String url = "https://www.coupang.com/np/search?q="+kWord
        		+ "&channel=user&sorter=saleCountDesc&listSize=48&"
        		+ "page=" + page;
        // Connection 객체 생성 및 헤더 설정
        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozila/5.0") // 유저 에이전트 설정
                .header("Accept-Language", "UTF-8"); // 언어 설정 등
        // HTTP 요청 보내기
        Document doc = connection.get();
        // 상품 리스트 가져오기
        Elements products = doc.select("li.search-product");
        // 각 상품 정보 출력
        
            for (int i = 0; i < products.size(); i++) {
           	 Element product = products.get(i);
           	 if(product.select("search-product__ad-badge").isEmpty()) {
                    String productName = product.select("div.name").text();
                    String originalPrice = product.select("del.base-price").text();
                    String salePrice = product.select("strong.price-value").text();
                    String rating = product.select("em.rating").text();
                    String reviewCount = product.select("span.rating-total-count").text();
                    String cardDiscount = product.select("span.ccid-txt").text();
                    String rewardInfo = product.select("span.reward-cash-txt").text();
                    String deliveryInfo = product.select("span.arrival-info").text();
                    String productId = product.attr("data-product-id");
                    String vendorId = product.attr("data-vendor-item-id");
                    if(productId.equals(pId)) {                    	 
                    // 각 상품 정보 출력
	                     productResult.put("productName", productName);
	                     productResult.put("originalPrice", originalPrice);
	                     productResult.put("salePrice", salePrice);
	                     productResult.put("rating", rating);
	                     productResult.put("reviewCount", reviewCount);
	                     productResult.put("cardDiscount", cardDiscount);
	                     productResult.put("rewardInfo", rewardInfo);
	                     productResult.put("deliveryInfo", deliveryInfo);
	                     productResult.put("productId", productId);
	                     productResult.put("vendorId", vendorId);
	                     productResult.put("rank", (page * (i+1)) + "");
	                     
	                     session.setAttribute("productCoupangDetail", productResult);
	                     // 세션 만료시간 설정 (1시간)
	                     session.setMaxInactiveInterval(60 * 60);
                    }
           	 	}
            }
            page++;
        }
    	 return productResult;
    }	
    
}
