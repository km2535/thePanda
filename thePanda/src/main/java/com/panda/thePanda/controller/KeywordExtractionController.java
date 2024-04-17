package com.panda.thePanda.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.service.crawler.CoupangProductCrawler;
import com.panda.thePanda.service.crawler.GmarketProductNameCrawler;
import com.panda.thePanda.service.crawler.KakaoProductCrawler;
import com.panda.thePanda.service.crawler.NaverProductNameCrawler;
import com.panda.thePanda.service.keyword_top.ProductTitleByKeyword;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/panda-v1/extraction")
public class KeywordExtractionController {

	private final GmarketProductNameCrawler gmarketProductNameCrawler;
	private final CoupangProductCrawler coupangProductNameCrawler;
	private final NaverProductNameCrawler naverProductNameCrawler;
	private final ProductTitleByKeyword titleByKeyword;
	private final KakaoProductCrawler kakaoProductCrawler;

	@Operation(summary = "지마켓 상품명 출력", description = "품명 출력")
	@GetMapping("/product-name/gmarket")
	public List<String> getProductListByGmarket(
			@Parameter(description = "검색 요소") @RequestParam String keyword) throws IOException {
		return gmarketProductNameCrawler.getProductNamesByKeyword(keyword);
	}

	@Operation(summary = "쿠팡 상품명 출력", description = "품명 출력")
	@GetMapping("/product-name/coupang")
	public List<String> getProductListBySearchCriteria(
			@Parameter(description = "검색 요소") @RequestParam String keyword) throws IOException {
		return coupangProductNameCrawler.getProductNameInCoupang(keyword);
	}

	@Operation(summary = "네이버 상품명 출력", description = "품명 출력")
	@GetMapping("/product-name/naver")
	public List<String> getProductListByNaver(
			@Parameter(description = "검색 요소") @RequestParam String keyword)
			throws IOException, GeneralSecurityException, UnirestException {

		return titleByKeyword.getNaverSpProductByKeyword(keyword);
	}

	@Operation(summary = "카카오 상품명 출력", description = "품명 출력")
	@GetMapping("/product-name/kakao")
	public List<String> getProductListByKakao(
			@Parameter(description = "검색 요소") @RequestParam String keyword)
			throws IOException, GeneralSecurityException, UnirestException {

		return kakaoProductCrawler.getProductNameKakao(keyword);
	}

}
