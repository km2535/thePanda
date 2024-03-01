package com.panda.thePanda.service.crawler.impl;

import java.util.List;

import com.panda.thePanda.dto.ProductSearchDTO;

public interface ProductNameCrawler {
	public List<String> getProductNamesByKeyword(ProductSearchDTO pdto);

}
