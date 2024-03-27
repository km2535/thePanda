package com.panda.thePanda.controller;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.service.crawler.DataLabTopKeywordCrawler;
import com.panda.thePanda.util.ListToString;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/panda-v1/datalab")
public class DataLabCrawlerController {
	private final DataLabTopKeywordCrawler dataLabTopKeywordCrawler;

	public DataLabCrawlerController(DataLabTopKeywordCrawler dataLabTopKeywordCrawler) {
		this.dataLabTopKeywordCrawler = dataLabTopKeywordCrawler;

	}

	@Operation(summary = "카테고리 별 상위 키워드 검색", description = "카테고리를 입력받아 상위 키워드를 검색하여 리스트를 전달합니다.")
	@PostMapping("/top/keyword")
	public Map<Integer, List<String>> getDataLabTopKeywordByCategory(
			@RequestBody int[] categorys) throws IOException {
		Map<Integer, List<String>> response = new HashMap<>();
		for (int item : categorys) {
			response.put(item, dataLabTopKeywordCrawler.crawlTopKeywordByCategory(item, 1));
		}
		return response;
	}

	@Operation(summary = "페이지 별 상위 키워드의 검색변화량", description = "페이지, 카테고리를 입력받고 각 키워드를 전달받아 변화량을 검색한 뒤 "
			+ "변화량 차이를 리스트로 전달합니다.")
	@PostMapping("/top/keyword-rate")
	public List<Map<String, Integer>> getDataLabTopKeywordRateUpdate(
			@RequestParam Integer category, int page) throws IOException {
		List<Map<String, Integer>> response = new ArrayList<>();
		List<String> list = new ArrayList<String>();
		for (int i = 1; i < page + 1; i++) {
			list.addAll(dataLabTopKeywordCrawler.crawlTopKeywordByCategory(category, i));
		}
		ListToString listToString = new ListToString();
		list = listToString.listToStringGenerator(list, 5);
		for (int i = 0; i < list.size(); i++) {
			response.add(dataLabTopKeywordCrawler.getTopKeywordOfRate(category, list.get(i)));
			if (i % 3 == 0 && i > 0) {
				try {
					Thread.sleep(1500); // 2초(2000 밀리초) 동안 쉬기
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		// 결과를 리턴받자
		return response;
	}

}