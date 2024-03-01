package com.panda.thePanda.dto;

public class ProductSearchDTO {
	private String keyword;
	private String category;
	private int pageCount;
	private int reviewCount;
	
	public ProductSearchDTO() {
		this.category = "";
		this.pageCount = 1;
		this.reviewCount = 50;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCnt) {
		this.pageCount = pageCnt;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCnt) {
		this.reviewCount = reviewCnt;
	}
	
}
