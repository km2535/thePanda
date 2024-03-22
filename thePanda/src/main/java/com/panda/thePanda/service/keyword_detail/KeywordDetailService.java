package com.panda.thePanda.service.keyword_detail;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.panda.thePanda.api.naver_search.NaverSearchAPI;
import com.panda.thePanda.repository.keyword_save.KeywordDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordDetailService {
  private final NaverSearchAPI searchAPI;
  private final KeywordDetailRepository detailRepository;

  public void getRltKeyword(String keyword) throws IOException, GeneralSecurityException, UnirestException {
    // 키워드를 가져와서 해당 키워드 단위로 검색함.
    // saveKeywordToCmp 함수 참고
    // 리스트를 받아서 리스트의 키워드를 기반으로 데이터를 저장함.
    // 새로운 리스트를 반환시켜서 카테고리도 저장시킴
  }
}
