package com.panda.thePanda.dto;

public class MultiProductSearchDTO {
	private String[] keywords;
	private String[] categories;
	private int pageCount;
	
	public MultiProductSearchDTO() {
		//최대 10으로 제한
		this.pageCount = 1;
	}
	
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCnt) {
		this.pageCount = pageCnt;
	}
	
	
}
