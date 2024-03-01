package com.panda.thePanda.dto;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DuplicatedKeywordSortDTO {
	private List<String> productName;
	private String keyword;
	public List<String> getProductName() {
		return productName;
	}
	public void setProductName(List<String> productName) {
		this.productName = productName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
