package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.dto.ProductSearchDTO;
import com.panda.thePanda.service.crawler.CoupangProductCrawler;
import com.panda.thePanda.service.crawler.GmarketProductNameCrawler;
import com.panda.thePanda.service.crawler.NaverProductNameCrawler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/panda-v1/extraction")
public class KeywordExtractionController {

	private final GmarketProductNameCrawler gmarketProductNameCrawler;
	private final CoupangProductCrawler coupangProductNameCrawler;
	private final NaverProductNameCrawler naverProductNameCrawler;

	public KeywordExtractionController(
			GmarketProductNameCrawler gmarketProductNameCrawler,
			CoupangProductCrawler coupangProductNameCrawler,
			NaverProductNameCrawler naverProductNameCrawler) {
		this.gmarketProductNameCrawler = gmarketProductNameCrawler;
		this.coupangProductNameCrawler = coupangProductNameCrawler;
		this.naverProductNameCrawler = naverProductNameCrawler;
	}

	@Operation(summary = "페이지 수 만큼 G마켓 상품명 리스트 출력", description = "키워드와 페이지를 입력 받아 G마켓에서 페이지 수 만큼 리스트를 만들어서 리턴")
	@PostMapping("/keyword/gmarket/product-name")
	public List<String> getGmarketProductNameByPage(
			@Parameter(description = "검색 요소") @RequestBody ProductSearchDTO pdto) throws IOException {
		List<String> productNameList = new ArrayList<String>();
		// 지마켓 크롤링.
		productNameList = gmarketProductNameCrawler.getProductNamesByKeyword(pdto);
		return productNameList;
	}

	@Operation(summary = "페이지 수 만큼 네이버 상품명 리스트 출력", description = "키워드와 페이지를 입력 받아 네이버에서 페이지 수 만큼 리스트를 만들어서 리턴")
	@PostMapping("/keyword/naver/product-name")
	public List<String> getNaverProductNameByPage(
			@Parameter(description = "검색 요소") @RequestBody ProductSearchDTO pdto) throws IOException {
		List<String> productNameList = new ArrayList<String>();
		// 네이버 크롤링.
		productNameList = naverProductNameCrawler.getProductNamesByKeyword(pdto);
		return productNameList;
	}

	@Operation(summary = "페이지 수 만큼 쿠팡 상품명 리스트 출력", description = "키워드와 페이지를 입력 받아 쿠팡에서 페이지 수 만큼 리스트를 만들어서 리턴")
	@PostMapping("/keyword/coupang/product-name")
	public List<Map<String, String>> getProductListBySearchCriteria(
			@Parameter(description = "검색 요소") @RequestBody ProductSearchDTO pdto) throws IOException {
		List<Map<String, String>> productNameList = coupangProductNameCrawler.getProductListBySearchCriteria(pdto);
		return productNameList;
	}
}
