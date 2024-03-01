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
import com.panda.thePanda.service.crawler.CoupangProductCrawler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/panda/api/coupang")
public class CoupangProductController {
	private final CoupangProductCrawler coupangProductNameCrawler;

	public CoupangProductController(CoupangProductCrawler coupangProductNameCrawler) {
		this.coupangProductNameCrawler = coupangProductNameCrawler;
	}

	@Operation(summary = "쿠팡 리뷰 달린 상품 목록", description = "키워드는 필수 그 외 카테고리, 리뷰수, 페이지 수는 기본값을 적용하여 리뷰가 달린 상품을 리턴합니다.")
	@PostMapping("/product/info")
	public List<Map<String, String>> getProductListBySearchCriteria(
			@Parameter(description = "검색 키워드") @RequestBody ProductSearchDTO pdto) throws IOException {

		return coupangProductNameCrawler.getProductListBySearchCriteria(pdto);
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
					.getProductListBySearchCriteria(productSearchDTO);
			result.put(keywords[i], productResult);

		}

		return result;
	}

	@Operation(summary = "쿠팡 리뷰 상품 분석", description = "쿠팡 리뷰를 분석하여 상품평 알바를 찾아냅니다.")
	@GetMapping("/product/review")
	public List<HashMap<String, HashMap<String, String>>> getWeeklyReviewsByFiftyReviewers(
			@Parameter(description = "검색 키워드") @RequestParam String productId) throws IOException {

		return coupangProductNameCrawler.getWeeklyReviewsByFiftyReviewers(productId);
	}

}
