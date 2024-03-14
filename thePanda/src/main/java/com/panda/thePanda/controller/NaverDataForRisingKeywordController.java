package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.entity.keyword_info_base_name.KeywordInfoCustomByRisingEntity;
import com.panda.thePanda.repository.keyword_info_base_name.KeywordInfoCustomByRisingRepository;
import com.panda.thePanda.service.get_keyword.GetCategoryService;
import com.panda.thePanda.service.get_keyword.GetKeywordService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/keyword")
@RequiredArgsConstructor
public class NaverDataForRisingKeywordController {
  private final GetKeywordService getKeywordService;
  private final GetCategoryService getCategoryService;
  private final KeywordInfoCustomByRisingRepository keywordInfoCustomByRisingRepository;

  @Operation(summary = "패션의류", description = "급상승 키워드 데이터를 불러옵니다.")
  @GetMapping("/naver/rising-keyword/50000000")
  public ResponseEntity<List<KeywordInfoCustomByRisingEntity>> getNaver50000000RisingKeyword() throws IOException {
    List<KeywordInfoCustomByRisingEntity> keywordInfoList = keywordInfoCustomByRisingRepository
        .get50000000RisingKeyword();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

}
