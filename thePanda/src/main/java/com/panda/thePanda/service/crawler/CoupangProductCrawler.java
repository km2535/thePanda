package com.panda.thePanda.service.crawler;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.panda.thePanda.dto.ProductSearchDTO;

@Component
public class CoupangProductCrawler {
	// 리뷰어 분석 함수
	// 리뷰어는 50인으로 제한한다.
	// 각 리뷰의 일주일 리뷰로 제한한다.
	public Map<String, Map<String, String>> getWeeklyReviewsByFiftyReviewers(String productId, int searchPage)
			throws IOException, InterruptedException {
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--incognito");
		// options.addArguments("--blink-settings=imagesEnabled=false");
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Map<String, Map<String, String>> productsWithReviewer = new HashMap<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get("https://www.coupang.com/vp/products/" + productId);
		try {
			// driver.manage().timeouts().implicitlyWait(Duration.ofMillis(6000));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("review")));
			driver.findElement(By.name("review")).click();
			wait.until(
					ExpectedConditions
							.visibilityOfElementLocated(
									By.cssSelector(".sdp-review__article__page.js_reviewArticlePagingContainer")));
		} catch (Exception e) {

		}
		for (int i = 1; i <= searchPage; i++) {

			try {
				// 리뷰어들 선택
				List<WebElement> reviews = driver
						.findElements(By.cssSelector(".sdp-review__article__list.js_reviewArticleReviewList"));
				for (WebElement reviewer : reviews) {
					try {
						reviewer
								.findElement(By.cssSelector(".sdp-review__article__list__info__user__name.js_reviewUserProfileImage"))
								.click();
						wait.until(
								ExpectedConditions
										.visibilityOfElementLocated(
												By.cssSelector(
														".sdp-review__modal.sdp-review__profile.js_reviewProfileModal.review-modal-active")));
					} catch (Exception e) {
						// TODO: handle exception
					}
					Map<String, String> products = new HashMap<>();

					try {
						WebElement articleElement = driver.findElement(
								By.cssSelector(".review__modal-groups.js_reviewModalGroup.review-modal-group-active"));
						js.executeScript(
								"arguments[0].scrollTop = arguments[0].scrollHeight; console.log(arguments[0].scrollHeight)",
								articleElement);
						wait.wait(2000, 10);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						String reviewerName = driver.findElement(By.cssSelector(".sdp-review__profile__article__info__name"))
								.getText();
						List<WebElement> reviewProducts = driver
								.findElements(By.cssSelector(".sdp-review__profile__article__list__reviews"));

						for (WebElement reviewProductName : reviewProducts) {
							String prodcutName = reviewProductName
									.findElement(By.cssSelector(".sdp-review__profile__article__list__reviews__product__name")).getText();
							String prodcutDate = reviewProductName
									.findElement(By.cssSelector(".sdp-review__profile__article__list__reviews__star__date")).getText();
							products.put(prodcutName, prodcutDate);
						}
						productsWithReviewer.put(reviewerName, products);
					} catch (Exception e) {
						// TODO: handle exception
					}
					// 만들어진 map 리스트에 넣기

					// 모달 창 끄기
					try {
						driver.findElement(By.cssSelector(".sdp-review__profile__article__close-btn.js_modalClose")).click();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				try {
					System.out.println(".sdp-review__article__page__num.js_reviewArticlePageBtn[data-page='" + (i + 1) + "']");

					driver
							.findElement(
									By.cssSelector(
											".sdp-review__article__page__num.js_reviewArticlePageBtn[data-page='" + (i + 1) + "']"))
							.click();
					wait.until(
							ExpectedConditions
									.elementToBeSelected(
											By.cssSelector(
													".sdp-review__article__page__num.sdp-review__article__page__num--active.js_reviewArticlePageBtn[data-page='"
															+ (i + 1) + "']")));
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return productsWithReviewer;
	}

	// jsoup을 통한 상품 리스트 반환
	public List<Map<String, String>> getProductListBySearchCriteria(ProductSearchDTO pdto) throws IOException {
		List<Map<String, String>> productList = new ArrayList<Map<String, String>>();

		for (int i = 1; i <= pdto.getPageCount(); i++) {

			// 키워드, 카테고리와 페이지 수로 검색을 하고
			String url = "https://www.coupang.com/np/search?q=" + URLEncoder.encode(pdto.getKeyword(), "UTF-8")
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
			// System.out.println(doc);
			// 리뷰가 존재하는 상품만 정보를 map에 담아서
			// 리스트에 추가한다.
			for (int j = 0; j < products.size(); j++) {
				Map<String, String> product = new HashMap<String, String>();
				Element item = products.get(j);
				if (item.select("em.rating").size() > 0) {
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
					if (deliveryInfo.equals("")) {
						product.put("deliveryInfo", "쿠팡 윙");
					} else if (!deliveryInfo.equals("")) {
						product.put("deliveryInfo", deliveryInfo);
					}
					if (rocket.equals("")) {
						product.put("rocketImgUrl", "");
						product.put("rocket", "일반");
					} else if (!rocket.equals("")) {
						product.put("rocketImgUrl", "https:" + rocket);
						product.put("rocket", "로켓");
					}
				}
				if (product.size() > 0) {
					productList.add(product);
				}
			}

		}

		return productList;
	}
}
