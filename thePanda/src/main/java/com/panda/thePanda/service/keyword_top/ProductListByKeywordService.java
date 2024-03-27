package com.panda.thePanda.service.keyword_top;

import org.springframework.stereotype.Service;

import com.panda.thePanda.entity.keyword_save.KeywordByProduct;
import com.panda.thePanda.repository.keyword_save.KeywordByProductsRepository;
import java.util.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductListByKeywordService {
  private final KeywordByProductsRepository productsRepository;

  public List<KeywordByProduct> getProductListByKeyword(String keyword) {
    return productsRepository.findByKeyword(keyword);
  }
}
