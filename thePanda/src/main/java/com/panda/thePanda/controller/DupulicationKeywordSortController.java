package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.dto.DuplicatedKeywordSortDTO;
import com.panda.thePanda.util.DupulicateKeywordArrange;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/panda/api/duplication")
public class DupulicationKeywordSortController {
	
	private final DupulicateKeywordArrange dupulicateKeywordArrange;
	public DupulicationKeywordSortController(DupulicateKeywordArrange dupulicateKeywordArrange) {
		this.dupulicateKeywordArrange = dupulicateKeywordArrange;
	}
	
	@Operation(summary = "상품 명들을 리스트 형태로 받아 중복 검사함(미사용)",
            description = "키워드 검색 결과를 리스트 형태로 받아 다시 정리하여 리턴시킴")
	 @PostMapping("/keyword/sort")
	 public Map<String, TreeMap<Integer, List<String>>> getKeywordMostDuplicated(
	 	 @Parameter(description = "키워드와 리스트 형태의 상품명들")
	 	 @RequestBody DuplicatedKeywordSortDTO duplicatedKeywordSortDTO
	 	 ) throws IOException {
		Map<String, TreeMap<Integer, List<String>>> productNameList = new HashMap<>();
			//중복 검사 키워드 
		TreeMap<Integer, List<String>> keyword = new TreeMap<>();
		List<String> list = new ArrayList<String>();
		list.add(duplicatedKeywordSortDTO.getKeyword());
		keyword.put(0, list);
			productNameList.put(
					"dupulicatedKeyword", 
					dupulicateKeywordArrange.dupulicateKeywordArrangeList(duplicatedKeywordSortDTO.getProductName()));
			productNameList.put("keyword", keyword);
					
		return productNameList;
	 }
}
