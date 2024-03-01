package com.panda.thePanda.service.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.panda.thePanda.util.GetAutoCompleteKeyword;

@Component
public class RelationalKeywordCrawler {
	private final GetAutoCompleteKeyword getauto;

	public RelationalKeywordCrawler(GetAutoCompleteKeyword getauto) {
		this.getauto = getauto;
	}

	// 네이버
	public HashMap<String, List<String>> naverCrawlRelatedTags(String keyword) {
		return this.hashReturnFunction("naver", keyword);
	}

	// 쿠팡
	public HashMap<String, List<String>> coupangCrawlRelatedTags(String keyword) {
		return this.hashReturnFunction("coupang", keyword);
	}

	// g마켓
	public HashMap<String, List<String>> gmarketCrawlRelatedTags(String keyword) {
		return this.hashReturnFunction("gmarket", keyword);
	}

	private HashMap<String, List<String>> hashReturnFunction(String webKind, String keyword) {
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		List<String> relateTags = new ArrayList<String>();
		List<String> autoKeyword = new ArrayList<String>();
		String url;
		String tags;
		switch (webKind) {
			case "naver":
				url = "https://search.shopping.naver.com/search/all?query=" + keyword;
				tags = ".relatedTags_relation_srh__YG9s7 > ul > li";
				autoKeyword = getauto.getAutoCompleteFromNaver(keyword);
				relateTags = RelationalKeywordCrawler.requestRelatedKeywords(url, tags);
				break;
			case "coupang":
				url = "https://www.coupang.com/np/search?component=&q=" + keyword + "&channel=user";
				tags = "dl.search-related-keyword > dd > a";
				autoKeyword = getauto.getAutoCompleteFromCoupang(keyword);
				relateTags = RelationalKeywordCrawler.requestRelatedKeywords(url, tags);
				break;
			case "gmarket":
				url = "https://browse.gmarket.co.kr/search?keyword=" + keyword;
				tags = ".box__component-header .list__keywords .list-item";
				autoKeyword = getauto.getAutoCompleteFromGmarket(keyword);
				relateTags = RelationalKeywordCrawler.requestRelatedKeywords(url, tags);
				break;
		}
		result.put("relateTags", relateTags);
		result.put("autoKeyword", autoKeyword);

		return result;
	}

	private static List<String> requestRelatedKeywords(String url, String selectTag) {
		List<String> relatedTags = new ArrayList<>();

		try {
			Connection connection = Jsoup.connect(url)
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
							+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
					.header("Accept-Language", "UTF-8");
			Document doc = connection.get();
			Elements tagElements = doc.select(selectTag);
			for (Element tagElement : tagElements) {
				String tagText = tagElement.text();
				if (!tagText.isEmpty()) {
					relatedTags.add(tagText);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return relatedTags;
	}
}
