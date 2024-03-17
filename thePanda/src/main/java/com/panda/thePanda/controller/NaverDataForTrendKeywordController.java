package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.panda.thePanda.entity.keyword_info_base_name.KeywordInfoCustomEntity;
import com.panda.thePanda.service.get_keyword.GetCategoryService;
import com.panda.thePanda.service.get_keyword.GetKeywordService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/keyword")
@RequiredArgsConstructor
public class NaverDataForTrendKeywordController {
  private final GetKeywordService getKeywordService;
  private final GetCategoryService getCategoryService;

  @Operation(summary = "모든 카테고리", description = "모든 카테고리 별 데이터(카테고리, 시즌상품)을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/00000000")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeywordBaseAll() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeywordInfoCustom();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션의류", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000000")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000000Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000000CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션잡화", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000001")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000001Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000001CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "화장품/미용", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000002")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000002Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000002CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "디지털/가전", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000003")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000003Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000003CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "가구/인테리어", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000004")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000004Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000004CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "출산/육아", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000005")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000005Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000005CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "식품", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000006")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000006Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000006CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "스포츠/레저", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000007")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000007Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000007CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "생활/건강", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000008")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000008Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000008CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "여가/생활편의", description = "카테고리 별 데이터(카테고리, 시즌상품) 을 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000009")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000009Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000009CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }
}
