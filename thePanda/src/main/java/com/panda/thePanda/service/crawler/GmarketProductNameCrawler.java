package com.panda.thePanda.service.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.panda.thePanda.dto.ProductSearchDTO;
import com.panda.thePanda.service.crawler.impl.ProductNameCrawler;

@Component
public class GmarketProductNameCrawler implements ProductNameCrawler{
	@Override
	public List<String> getProductNamesByKeyword(ProductSearchDTO pdto) {
		 	List<String> productNames = new ArrayList<>();
		 	
		 	for(int i = 1; i <= pdto.getPageCount(); i++) {
		 		String url = "https://browse.gmarket.co.kr/search?keyword=" + pdto.getKeyword() + "&p=" + i;

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

		 	}
	        return productNames;
	}
}
