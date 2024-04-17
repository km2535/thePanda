package com.panda.thePanda.service.crawler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.panda.thePanda.dto.ProductInfo;
import com.panda.thePanda.dto.ProductSearchDTO;
import com.panda.thePanda.service.crawler.impl.ProductNameCrawler;

@Component
public class NaverProductNameCrawler implements ProductNameCrawler {
	@Override
	public List<String> getProductDetailByKeyword(ProductSearchDTO pdto) {
		List<String> productNames = new ArrayList<>();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		WebDriver driver = new ChromeDriver(options);
		for (int i = 1; i <= pdto.getPageCount(); i++) {
			String url = "https://search.shopping.naver.com/search/all?query=" + pdto.getKeyword()
					+ "&pagingSize=80&viewType=image&pagingIndex=" + i;
			driver.get(url);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			WebElement productList = driver.findElement(By.className("imgList_img_list__vbdJ0"));
			List<WebElement> productElements = productList.findElements(By.className("product_list_item__Y4XcD"));
			for (WebElement element : productElements) {
				String productName = element.findElement(By.className("product_link__W2i_v")).getText();
				productNames.add(productName);
			}
		}
		driver.quit();
		System.out.println(productNames.size());

		return productNames;
	}

	public List<ProductInfo> getProductByKeyword(String keyword) {
		List<ProductInfo> purchaseCountList = new ArrayList<>();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("detach", true);
		options.addArguments("disable-blink-features=AutomationControlled");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
		options.addArguments("--disable-extensions"); // 확장 기능 사용 비활성화
		options.addArguments("--disable-popup-blocking"); // 팝업 차단 비활성화
		options.addArguments("--disable-notifications"); // 알림 비활성화
		options.addArguments(
				"--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
		options.addArguments("--header=Accept: */*");
		options.addArguments("--header=Accept-Encoding: gzip, deflate, br, zstd");
		options.addArguments("--header=Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
		options.addArguments("--header=Connection: keep-alive");
		options.addArguments("--header=Content-Length: 598");
		options.addArguments("--header=Content-Type: application/x-www-form-urlencoded; charset=UTF-8");
		options.addArguments("--header=Host: nelo2-col.navercorp.com");
		options.addArguments("--header=Origin: https://search.shopping.naver.com");
		options.addArguments("--header=Referer: https://search.shopping.naver.com/search/all?query=" + keyword);
		WebDriver driver = new ChromeDriver(options);

		// String url = "https://search.shopping.naver.com/search/all?adQuery=" +
		// keyword +
		// "&origQuery=" + keyword +
		// "&pagingIndex=1&pagingSize=80&productSet=total&query=" + keyword +
		// "&sort=rel&timestamp=&viewType=image";
		String url = "https://search.shopping.naver.com/search/all?query=" + keyword;
		driver.get(url);
		((JavascriptExecutor) driver)
				.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => false})");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// '구매' 버튼을 가진 요소들 가져오기
		List<WebElement> purchaseElements = driver.findElements(By.xpath("//a[contains(text(), '구매')]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

		// 각 요소에서 가격과 구매 건수를 추출하여 객체에 저장하고 리스트에 추가
		for (WebElement purchaseElement : purchaseElements) {
			// 상품 정보 영역의 부모 요소 가져오기
			WebElement parentElement = purchaseElement
					.findElement(By.xpath("./ancestor::div[@class='product_info_area__KU5QS']"));
			// 가격 요소 가져오기
			WebElement priceElement = parentElement.findElement(
					By.xpath(".//strong[contains(@class, 'product_price_area__d9UzR')]//span[@class='price_num__S2p_v']/em"));
			// 가격 텍스트 가져오기
			String productPrice = priceElement.getText();
			// 구매 건수 요소 가져오기
			String productPurchaseCount = purchaseElement.findElement(By.className("product_num__qLoWR")).getText();

			// 가격과 구매 건수를 저장하는 객체를 생성하여 리스트에 추가
			ProductInfo productInfo = new ProductInfo(productPrice, productPurchaseCount);
			purchaseCountList.add(productInfo);
		}

		// WebDriver 종료
		// driver.quit();

		return purchaseCountList;
	}
}
