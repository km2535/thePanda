package com.panda.thePanda.service.crawler;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import com.panda.thePanda.dto.ProductSearchDTO;

@Component
public class CoupangProductCrawler {
	// 리뷰어 분석 함수 
	// 리뷰어는 50인으로 제한한다.
	// 각 리뷰의 일주일 리뷰로 제한한다. 
	public List<HashMap<String, HashMap<String, String>>> getWeeklyReviewsByFiftyReviewers(String productId) {
		List<HashMap<String, HashMap<String, String>>> result = new ArrayList<>();
	 	ChromeOptions options = new ChromeOptions();
	 	 options.addArguments("--incognito"); 
	 	options.addArguments("--blink-settings=imagesEnabled=false");
		WebDriver driver = new ChromeDriver(options);
     
        	driver.get("https://www.coupang.com/vp/products/"+productId);
        	try {
          	List<WebElement> reviewText = new ArrayList<WebElement>();
        		for (int i = 0; i < 10; i++) {
                driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);  // 내리기
                try {
                    TimeUnit.SECONDS.sleep(1);
                    WebElement reviewTextElement = driver.findElement(By.className("js_reviewArticleListContainer"));
                    List<WebElement> review =  reviewTextElement.findElements(By.className("sdp-review__article__list__info__user__name"));
                    for(WebElement item : review) {
                    	reviewText.add(item);
                    }
                                	 
                } catch (InterruptedException e) {
                    continue;
                }catch (NoSuchElementException e) {                	
                	continue;
                }
            }
        		for(WebElement item : reviewText) {
        			System.out.println(item.getText());
        			//item을 클릭 후 sdp-review__profile__article가 나올 때까지 기다림
        			 item.click();
        			 //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); // 최대 대기 시간은 10초로 설정

        	            // 클래스 이름이 변경될 때까지 기다리기
        	           //WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("sdp-review__modal sdp-review__profile.js_reviewProfileModal.review-modal-active")));

                     // 상세 정보 창에서 데이터를 가져와서 처리합니다.
                     //WebElement detailElement = driver.findElement(By.className("sdp-review__profile__article"));
        		}
        		
        }catch(Exception e) {
        	
        }finally {
        	driver.quit();
		}
        	
            

	    
        return result;
	}
	
	//jsoup을 통한 상품 리스트 반환
	public List<Map<String, String>> getProductListBySearchCriteria(ProductSearchDTO pdto) throws IOException{
		List<Map<String, String>> productList = new ArrayList<Map<String,String>>();
		
		for(int i = 1; i <= pdto.getPageCount(); i++) {
			
		//키워드, 카테고리와 페이지 수로 검색을 하고 
		 String url = "https://www.coupang.com/np/search?q="+URLEncoder.encode(pdto.getKeyword(), "UTF-8")
	        		+ "&channel=user&sorter=saleCountDesc&listSize=48&component=" + URLEncoder.encode(pdto.getCategory(), "UTF-8")
	        		+ "&page=" + i;
		 // Connection 객체 생성 및 헤더 설정
	        Connection connection = Jsoup.connect(url)
	        		.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
							+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
					.header("Accept-Language", "UTF-8");
	        // HTTP 요청 보내기
	        Document doc = connection.get();
	        // 상품 리스트 가져오기
	        Elements products = doc.select("li.search-product");
	        //System.out.println(doc);
		// 리뷰가 존재하는 상품만 정보를 map에 담아서
		// 리스트에 추가한다.
	        for(int j = 0; j<products.size(); j++) {
	        	Map<String, String> product = new HashMap<String, String>();
	        	 Element item = products.get(j);
	        	 if(item.select("em.rating").size()>0) {
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
	                    product.put("productName", productName);
	                    product.put("originalPrice", originalPrice);
	                    product.put("salePrice", salePrice);
	                    product.put("rating", rating);
	                    product.put("reviewCount", reviewCount);
	                    product.put("rewardInfo", rewardInfo);
	                    product.put("productId", productId);
	                    product.put("vendorId", vendorId);
	                    if(deliveryInfo.equals("")) {
	                    	product.put("deliveryInfo", "쿠팡 윙");	                    	
	                    }else if(!deliveryInfo.equals("")) {
	                    	product.put("deliveryInfo", deliveryInfo);	                    		                    	
	                    }
	                    if(rocket.equals("")) {
	                    	product.put("rocketImgUrl", "");	                    		                    	
	                    	product.put("rocket", "일반");	                    		                    	
	                    }else if(!rocket.equals("")) {
	                    	product.put("rocketImgUrl", "https:"+rocket);	                    	
	                    	product.put("rocket", "로켓");	                    		                    	
	                    }
	        	 }
	        	 if(product.size() > 0) {
	        		 productList.add(product);
	        	 }
	        }
	        
		}
		
		return productList;		
	}
}
