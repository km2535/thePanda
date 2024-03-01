package com.panda.thePanda.service.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.panda.thePanda.exception.NoProductFoundException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class NaverTopProductNameCrawler {

	public String getTopProductKeyword(String keyword) throws NoProductFoundException {
		String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
		String url = "https://search.shopping.naver.com/search/all?query=" + encodedKeyword;
		try {
			Document document = Jsoup.connect(url).get();
			Elements basicList = document.select("div.basicList_list_basis__uNBZx");
			String keywordFromList = extractKeywordFromList(basicList);
			if (keywordFromList != null) {
				return keywordFromList;
			} else {
				throw new NoProductFoundException("No product found for the keyword: " + keyword);
			}
		} catch (IOException e) {
			handleException(e);
			throw new NoProductFoundException("Error occurred while fetching product data.");
		}
	}

	private String extractKeywordFromList(Elements basicList) {
		for (Element element : basicList) {
			Elements productItems = element.select("div.product_title__Mmw2K");
			String keywordFromItems = extractKeywordFromItems(productItems);
			if (keywordFromItems != null) {
				return keywordFromItems;
			}
		}
		return null;
	}

	private String extractKeywordFromItems(Elements productItems) {
		if (!productItems.isEmpty()) {
			Element firstProductItem = productItems.first();
			if (firstProductItem != null) {
				return firstProductItem.text();
			}
		}
		return null;
	}

	private void handleException(IOException e) {
		e.printStackTrace();
	}
}
