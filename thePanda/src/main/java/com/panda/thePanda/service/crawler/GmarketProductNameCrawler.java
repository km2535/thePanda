package com.panda.thePanda.service.crawler;

import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class GmarketProductNameCrawler {

	public List<Map<String, String>> getProductDetailByKeyword(String keyword) {
		List<Map<String, String>> products = new ArrayList<>();

		// String url = "https://browse.gmarket.co.kr/search?keyword=" + keyword +
		// "&s=7&k=42";
		String url = "https://www.gmarket.co.kr/n/search?keyword=" + keyword + "&s=7";

		try {
			Connection connection = Jsoup.connect(url)
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
							+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
					.header("Accept-Language", "UTF-8");
			Document doc = connection.get();

			Elements elements = doc
					.select(".box__component.box__component-itemcard.box__component-itemcard--general .box__item-container");

			for (Element element : elements) {
				Map<String, String> productDetail = new HashMap<>();
				String productName = element.select("span.text__item").text();
				String productUrl = element.select(".text__item-title.text__item-title--ellipsis .link__item").attr("href");
				String originalPrice = element.select("div.box__price-original span.text.text__value").text();
				String salesPrice = element.select("div.box__price-seller strong.text.text__value").text();
				String awardPoints = element.select(".box__seller-awards .image__awards-points .for-a11y").text();
				String reviewCount = element.select(".list-item.list-item__feedback-count .text").text();
				String sellingCount = element.select(".list-item.list-item__pay-count .text").text();
				String freeDelivery = element.select(".box__information-tags .text__tag img").attr("alt");
				String DeliveryInfo = element.select(".box__item-arrival .list-item__tag .text__tag").text();
				productDetail.put("productName", productName);
				productDetail.put("originalPrice", originalPrice);
				productDetail.put("salesPrice", salesPrice);
				productDetail.put("awardPoints", awardPoints);
				productDetail.put("reviewCount", reviewCount);
				productDetail.put("sellingCount", sellingCount);
				productDetail.put("freeDelivery", freeDelivery);
				productDetail.put("DeliveryInfo", DeliveryInfo);
				productDetail.put("productUrl", productUrl);
				products.add(productDetail);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return products;
	}

	public List<String> getProductNamesByKeyword(String keyword) {
		List<String> productNames = new ArrayList<>();

		String url = "https://browse.gmarket.co.kr/search?keyword=" + keyword + "&p=1";

		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("span.text__item");

			for (Element element : elements) {
				String productName = element.text();
				productNames.add(productName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productNames;
	}
}
