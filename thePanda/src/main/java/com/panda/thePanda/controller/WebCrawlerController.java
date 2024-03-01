package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.exception.NoProductFoundException;
import com.panda.thePanda.service.crawler.CoupangProductCrawler;
import com.panda.thePanda.service.crawler.NaverTopProductNameCrawler;
import com.panda.thePanda.service.crawler.RelationalKeywordCrawler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/panda/api/search")
public class WebCrawlerController {

	private final RelationalKeywordCrawler relationalKeywordCrawler;
	private final NaverTopProductNameCrawler naverTopProductNameCrawler;
	private final CoupangProductCrawler coupangProductNameCrawler;

	public WebCrawlerController(RelationalKeywordCrawler relationalKeywordCrawler,
			NaverTopProductNameCrawler naverTopProductNameCrawler,
			CoupangProductCrawler coupangProductNameCrawler) {
		this.relationalKeywordCrawler = relationalKeywordCrawler;
		this.coupangProductNameCrawler = coupangProductNameCrawler;
		this.naverTopProductNameCrawler = naverTopProductNameCrawler;
	}

	@Operation(summary = "네이버 연관 검색어 크롤링", description = "검색 키워드를 쿼리스트링으로 전달하면 네이버 웹을 크롤링 후 연관 검색어를 리턴합니다.")
	@GetMapping("/naver/relation-word")
	public HashMap<String, List<String>> getSearchNaverRelatedTags(
			@Parameter(description = "검색 키워드") @RequestParam String keyword) throws IOException {
		return relationalKeywordCrawler.naverCrawlRelatedTags(keyword);
	}

	@Operation(summary = "쿠팡 연관 검색어 크롤링", description = "검색 키워드를 쿼리스트링으로 전달하면 쿠팡 웹을 크롤링 후 연관 검색어를 리턴합니다.")
	@GetMapping("/coupang/relation-word")
	public HashMap<String, List<String>> getSearchCoupangRelatedTags(
			@Parameter(description = "검색 키워드") @RequestParam String keyword) throws IOException {
		return relationalKeywordCrawler.coupangCrawlRelatedTags(keyword);
	}

	@Operation(summary = "G마켓 연관 검색어 크롤링", description = "검색 키워드를 쿼리스트링으로 전달하면 G마켓 웹을 크롤링 후 연관 검색어를 리턴합니다.")
	@GetMapping("/gmarket/relation-word")
	public HashMap<String, List<String>> getSearchGmarketRelatedTags(
			@Parameter(description = "검색 키워드") @RequestParam String keyword) throws IOException {
		return relationalKeywordCrawler.gmarketCrawlRelatedTags(keyword);
	}

	@Operation(summary = "1순위 상품명 추출", description = ",로 나열된 키워드를 받아 검색하여 상위 상품을 전달 받음")
	@PostMapping("/naver/top-keyword")
	public List<String> getTopKeywordAndMakeNewKeyword(
			@Parameter(description = ",로 구분되어 있는 키워드") @RequestBody String keyword)
			throws IOException, NoProductFoundException {
		String[] keywords = keyword.split(",");
		List<String> topKeywords = new ArrayList<>();
		for (String word : keywords) {
			String searchKeyword = word.trim();
			String topKeyword = naverTopProductNameCrawler.getTopProductKeyword(searchKeyword);
			topKeywords.add(topKeyword);
		}
		return topKeywords;
	}

}
