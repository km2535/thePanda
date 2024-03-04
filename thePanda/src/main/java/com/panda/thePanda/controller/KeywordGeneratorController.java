package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.dto.GeneratorNewKeywordDTO;
import com.panda.thePanda.dto.MainAndSubKeywordsDTO;
import com.panda.thePanda.util.DupulicateKeywordArrange;
import com.panda.thePanda.util.GeneratorNewKeyword;
import com.panda.thePanda.util.KeywordRecommendationGenerator;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/panda-v1/generator")
public class KeywordGeneratorController {

	private final DupulicateKeywordArrange dupulicateKeywordArrange;
	private final GeneratorNewKeyword generatorNewKeyword;
	private final KeywordRecommendationGenerator keywordRecommendationGenerator;

	public KeywordGeneratorController(DupulicateKeywordArrange dupulicateKeywordArrange,
			GeneratorNewKeyword generatorNewKeyword,
			KeywordRecommendationGenerator keywordRecommendationGenerator) {
		this.dupulicateKeywordArrange = dupulicateKeywordArrange;
		this.generatorNewKeyword = generatorNewKeyword;
		this.keywordRecommendationGenerator = keywordRecommendationGenerator;

	}

	@Operation(summary = "상품명 키워드 중복 검사", description = "여러 상품명을 조사하여 중복 횟수가 많은 키워드 순으로 정렬하여 리턴합니다.")
	@PostMapping("/most-dupuliacte/keyword")
	public Map<Integer, List<String>> getMostDupulicatedKeywordList(

			@RequestBody List<String> productName) throws IOException {
		// 키워드 중복 확인 클래스
		Map<Integer, List<String>> response = dupulicateKeywordArrange.dupulicateKeywordArrangeList(productName);
		return response;
	}

	@Operation(summary = "새로운 상품명을 추천합니다.", description = "여러 상품명을 조사하여 중복 횟수가 많은 키워드 순으로 정렬하고 새로운 상품명을 만듭니다.")
	@PostMapping("/new-recommand/keyword")
	public List<String> getMostDupulicatedKeywordListAndReturnGeneratorNewKeyword(
			@RequestBody GeneratorNewKeywordDTO generatorNewKeywordDTO) throws IOException {
		// 키워드 중복 확인 클래스
		Map<Integer, List<String>> response = dupulicateKeywordArrange.dupulicateKeywordArrangeList(
				generatorNewKeywordDTO.getProductName());
		// 새로운 키워드 생성클래스

		List<String> result = generatorNewKeyword.recommandNewKeyword(response,
				generatorNewKeywordDTO.getKeywordLengthLimit());
		return result;
	}

	@Operation(summary = "여러 키워드를 받아 새로운 키워드를 생성합니다.", description = "메인 키워드는 필수, 서브키워드를 받아 50자 미만의 새로운 키워드를 추천합니다.")
	@PostMapping("/generator/keyword")
	public List<String> recommendNewKeywordsFromMultipleKeywords(
			@RequestBody MainAndSubKeywordsDTO mainAndSubKeywordsDTO) throws IOException {
		// 새로운 키워드를 리스트 형으로 반환합니다.
		return keywordRecommendationGenerator.generateRecommendedKeywords(mainAndSubKeywordsDTO);
	}

}
