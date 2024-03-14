package com.panda.thePanda.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.entity.keyword_info.KeywordInfo50000000Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000001Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000002Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000003Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000004Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000005Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000006Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000007Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000008Entity;
import com.panda.thePanda.entity.keyword_info.KeywordInfo50000009Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000000Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000001Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000002Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000003Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000004Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000005Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000006Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000007Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000008Entity;
import com.panda.thePanda.entity.keyword_info_base.KeywordInfoBaseName50000009Entity;
import com.panda.thePanda.entity.keyword_info_base_name.KeywordInfoCustomEntity;
import com.panda.thePanda.service.get_keyword.GetCategoryService;
import com.panda.thePanda.service.get_keyword.GetKeywordService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panda-v1/keyword")
@RequiredArgsConstructor
public class NaverDataForRealTimeKeywordController {
  private final GetKeywordService getKeywordService;
  private final GetCategoryService getCategoryService;

  @Operation(summary = "패션의류", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000000")
  public ResponseEntity<List<KeywordInfo50000000Entity>> getNaverTrendKeyword50000000() throws IOException {
    List<KeywordInfo50000000Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000000();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션잡화", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000001")
  public ResponseEntity<List<KeywordInfo50000001Entity>> getNaverTrendKeyword50000001() throws IOException {
    List<KeywordInfo50000001Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000001();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "화장품/미용", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000002")
  public ResponseEntity<List<KeywordInfo50000002Entity>> getNaverTrendKeyword50000002() throws IOException {
    List<KeywordInfo50000002Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000002();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "디지털/가전", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000003")
  public ResponseEntity<List<KeywordInfo50000003Entity>> getNaverTrendKeyword50000003() throws IOException {
    List<KeywordInfo50000003Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000003();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "가구/인테리어", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000004")
  public ResponseEntity<List<KeywordInfo50000004Entity>> getNaverTrendKeyword50000004() throws IOException {
    List<KeywordInfo50000004Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000004();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "출산/육아", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000005")
  public ResponseEntity<List<KeywordInfo50000005Entity>> getNaverTrendKeyword50000005() throws IOException {
    List<KeywordInfo50000005Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000005();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "식품", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000006")
  public ResponseEntity<List<KeywordInfo50000006Entity>> getNaverTrendKeyword50000006() throws IOException {
    List<KeywordInfo50000006Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000006();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "스포츠/레저", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000007")
  public ResponseEntity<List<KeywordInfo50000007Entity>> getNaverTrendKeyword50000007() throws IOException {
    List<KeywordInfo50000007Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000007();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "생활/건강", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000008")
  public ResponseEntity<List<KeywordInfo50000008Entity>> getNaverTrendKeyword50000008() throws IOException {
    List<KeywordInfo50000008Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000008();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "여가/생활편의", description = "카테고리 별 데이터를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/50000009")
  public ResponseEntity<List<KeywordInfo50000009Entity>> getNaverTrendKeyword50000009() throws IOException {
    List<KeywordInfo50000009Entity> keywordInfoList = getKeywordService.getKeywordInfoByCategory50000009();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션의류", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000000")
  public ResponseEntity<List<KeywordInfoBaseName50000000Entity>> getNaverTrendKeywordBase50000000() throws IOException {
    List<KeywordInfoBaseName50000000Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000000();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션잡화", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000001")
  public ResponseEntity<List<KeywordInfoBaseName50000001Entity>> getNaverTrendKeywordBase50000001() throws IOException {
    List<KeywordInfoBaseName50000001Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000001();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "화장품/미용", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000002")
  public ResponseEntity<List<KeywordInfoBaseName50000002Entity>> getNaverTrendKeywordBase50000002() throws IOException {
    List<KeywordInfoBaseName50000002Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000002();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "디지털/가전", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000003")
  public ResponseEntity<List<KeywordInfoBaseName50000003Entity>> getNaverTrendKeywordBase50000003() throws IOException {
    List<KeywordInfoBaseName50000003Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000003();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "가구/인테리어", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000004")
  public ResponseEntity<List<KeywordInfoBaseName50000004Entity>> getNaverTrendKeywordBase50000004() throws IOException {
    List<KeywordInfoBaseName50000004Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000004();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "출산/육아", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000005")
  public ResponseEntity<List<KeywordInfoBaseName50000005Entity>> getNaverTrendKeywordBase50000005() throws IOException {
    List<KeywordInfoBaseName50000005Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000005();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "식품", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000006")
  public ResponseEntity<List<KeywordInfoBaseName50000006Entity>> getNaverTrendKeywordBase50000006() throws IOException {
    List<KeywordInfoBaseName50000006Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000006();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "스포츠/레저", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000007")
  public ResponseEntity<List<KeywordInfoBaseName50000007Entity>> getNaverTrendKeywordBase50000007() throws IOException {
    List<KeywordInfoBaseName50000007Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000007();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "생활건강", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000008")
  public ResponseEntity<List<KeywordInfoBaseName50000008Entity>> getNaverTrendKeywordBase50000008() throws IOException {
    List<KeywordInfoBaseName50000008Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000008();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "여가/생활편의", description = "카테고리 별 데이터(카테고리, 시즌상품)를 불러옵니다.")
  @GetMapping("/naver/trend-keyword/base-50000009")
  public ResponseEntity<List<KeywordInfoBaseName50000009Entity>> getNaverTrendKeywordBase50000009() throws IOException {
    List<KeywordInfoBaseName50000009Entity> keywordInfoList = getCategoryService.getKeywordInfoByCategory50000009();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "모든 카테고리", description = "모든 카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/00000000")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeywordBaseAll() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeywordInfoCustom();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션의류", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000000")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000000Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000000CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "패션잡화", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000001")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000001Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000001CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "화장품/미용", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000002")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000002Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000002CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "디지털/가전", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000003")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000003Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000003CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "가구/인테리어", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000004")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000004Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000004CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "출산/육아", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000005")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000005Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000005CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "식품", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000006")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000006Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000006CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "스포츠/레저", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000007")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000007Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000007CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "생활/건강", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000008")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000008Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000008CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }

  @Operation(summary = "여가/생활편의", description = "카테고리 별 데이터(카테고리, 시즌상품) top100을 불러옵니다.")
  @GetMapping("/naver/top100/50000009")
  public ResponseEntity<List<KeywordInfoCustomEntity>> getNaverTrendKeyword50000009Top() throws IOException {
    List<KeywordInfoCustomEntity> keywordInfoList = getKeywordService.getKeyword50000009CategoryTop();
    return new ResponseEntity<>(keywordInfoList, HttpStatus.OK);
  }
}
