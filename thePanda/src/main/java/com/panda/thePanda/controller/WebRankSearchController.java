package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.service.crawler.RankingFindOnCoupangCrawler;
import com.panda.thePanda.service.crawler.RankingFindOnNaverCrawler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/panda-v1/ranking")
public class WebRankSearchController {

	private final RankingFindOnNaverCrawler rankingNaverFinder;
	private final RankingFindOnCoupangCrawler rankingCoupangFinder;

	public WebRankSearchController(RankingFindOnNaverCrawler rankingNaverFinder,
			RankingFindOnCoupangCrawler rankingCoupangFinder) {
		this.rankingNaverFinder = rankingNaverFinder;
		this.rankingCoupangFinder = rankingCoupangFinder;
	}

	@Operation(summary = "nvMid, 키워드를 입력하면 순위를 찾아 세션에 저장(single | 네이버)", description = "네이버 쇼핑을 검색하여 5페이지까지 검색하여 조건에 맞는 상품의 순위를 리턴한다.")
	@GetMapping("/panda/api/ranking/search-naver/rank-nvmid/single")
	public Map<String, String> getSearchNaverRanknvMidSingle(
			@Parameter(description = "nvMid") @RequestParam String productNvMid,
			@Parameter(description = "키워드") @RequestParam String keyword) throws IOException {
		Map<String, String> keywordRank = new HashMap<>();
		keywordRank = rankingNaverFinder.RankingFindBynvMid(productNvMid, keyword);
		return keywordRank;
	}

	@Operation(summary = "productId, itemId, 키워드를 입력하면 순위를 찾아 세션에 저장(single | 쿠팡)", description = "쿠팡 쇼핑을 검색하여 5페이지까지 검색하여 조건에 맞는 상품의 순위를 리턴한다.")
	@GetMapping("/search-coupang/rank-id/single")
	public Map<String, String> getSearchCoupangRankId(
			@Parameter(description = "productId") @RequestParam String productId,
			@Parameter(description = "vendorId") @RequestParam(required = false) String vendorId,
			@Parameter(description = "키워드") @RequestParam String keyword) throws IOException {
		Map<String, String> keywordRank = new HashMap<>();
		keywordRank = rankingCoupangFinder.rankingFindByProductId(productId, vendorId, keyword);
		return keywordRank;
	}

}
