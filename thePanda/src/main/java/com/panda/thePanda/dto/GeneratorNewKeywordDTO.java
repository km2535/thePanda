package com.panda.thePanda.dto;

import java.util.List;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public class GeneratorNewKeywordDTO {
	 private List<String> productName;
     private int keywordLengthLimit;
	public List<String> getProductName() {
		return productName;
	}
	public void setProductName(List<String> productName) {
		this.productName = productName;
	}
	public int getKeywordLengthLimit() {
		return keywordLengthLimit;
	}
	public void setKeywordLengthLimit(int keywordLengthLimit) {
		this.keywordLengthLimit = keywordLengthLimit;
	}     
     
}
