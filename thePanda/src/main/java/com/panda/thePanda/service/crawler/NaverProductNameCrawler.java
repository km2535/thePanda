package com.panda.thePanda.service.crawler;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import com.panda.thePanda.dto.ProductSearchDTO;
import com.panda.thePanda.service.crawler.impl.ProductNameCrawler;

@Component
public class NaverProductNameCrawler implements ProductNameCrawler{
	@Override
	public List<String> getProductNamesByKeyword(ProductSearchDTO pdto) {
		 List<String> productNames = new ArrayList<>();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless");

	        WebDriver driver = new ChromeDriver(options);
	        for (int i = 1; i <= pdto.getPageCount(); i++) {
	            String url = "https://search.shopping.naver.com/search/all?query=" + pdto.getKeyword()
	            		+ "&pagingSize=40&viewType=image&pagingIndex=" + i;
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
	       
	        return productNames;
	}
}
