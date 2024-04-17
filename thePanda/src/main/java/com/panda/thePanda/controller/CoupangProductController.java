package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.dto.MultiProductSearchDTO;
import com.panda.thePanda.dto.ProductSearchDTO;
import com.panda.thePanda.entity.keyword_save.KeywordForRankCoupang;
import com.panda.thePanda.service.crawler.CoupangProductCrawler;
import com.panda.thePanda.service.keyword_top.KeywordRankTrackingCoupang;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/coupang")
@RequiredArgsConstructor
public class CoupangProductController {
	private final CoupangProductCrawler coupangProductNameCrawler;
	private final KeywordRankTrackingCoupang trackingCoupang;

	@Operation(summary = "쿠팡 리뷰 달린 상품 목록", description = "키워드는 필수 그 외 카테고리, 리뷰수, 페이지 수는 기본값을 적용하여 리뷰가 달린 상품을 리턴합니다.")
	@GetMapping("/product/info")
	public List<Map<String, String>> getProductListBySearchCriteria(
			@Parameter(description = "검색 키워드") @RequestParam String keyword) throws IOException {

		return coupangProductNameCrawler.getProductListBySearchCriteria(keyword);
	}

	@Operation(summary = "쿠팡 상품을 여러 키워드로 검색", description = "여러 키워드로 검색하며 키워드는 필수 그 외 카테고리, 리뷰수, 페이지 수는 기본값을 적용하여 리뷰가 달린 상품을 리턴합니다.")
	@PostMapping("/multi-product/info")
	public Map<String, List<Map<String, String>>> getProductInfoByMultipleKeywords(
			@Parameter(description = "검색 키워드") @RequestBody MultiProductSearchDTO multiProductSearchDTO) throws IOException {
		Map<String, List<Map<String, String>>> result = new HashMap<>();
		String[] keywords = multiProductSearchDTO.getKeywords();
		String[] categories = multiProductSearchDTO.getCategories();
		final int MAX_PAGE_COUNT = 5;

		for (int i = 0; i < keywords.length; i++) {
			int pageCount = Math.min(multiProductSearchDTO.getPageCount(), MAX_PAGE_COUNT);
			ProductSearchDTO productSearchDTO = new ProductSearchDTO();
			productSearchDTO.setPageCount(pageCount);
			productSearchDTO.setKeyword(keywords[i]);
			productSearchDTO.setCategory(categories[i]);
			List<Map<String, String>> productResult = coupangProductNameCrawler
					.getProductListBySearchCriteria(productSearchDTO.getKeyword());
			result.put(keywords[i], productResult);

		}

		return result;
	}

	@Operation(summary = "쿠팡 리뷰 상품 분석", description = "쿠팡 리뷰를 분석하여 상품평 알바를 찾아냅니다.")
	@GetMapping("/product/review")
	public Map<String, Map<String, String>> getWeeklyReviewsByFiftyReviewers(
			@Parameter(description = "검색 키워드") @RequestParam String productId,
			@Parameter(description = "검색 페이지(페이지 당 5인)") @RequestParam int searchPage)
			throws IOException, InterruptedException {

		return coupangProductNameCrawler.getWeeklyReviewsByFiftyReviewers(productId, searchPage);
	}

	@Operation(summary = "해당 상품의 쿠팡 순위를 리턴합니다.", description = "해당 상품의 쿠팡 순위를 리턴합니다.")
	@GetMapping("/coupang-tracking/rank")
	public HashMap<String, KeywordForRankCoupang> getRankTrackerByCoupang(
			@Parameter(description = "검색 키워드") @RequestParam String keywords,
			@Parameter(description = "상품 id") @RequestParam String productId,
			@Parameter(description = "정렬 방법") @RequestParam String sorted)
			throws IOException, InterruptedException {

		return trackingCoupang.getRankByCoupangProductList(keywords, productId, sorted);
	}

}
