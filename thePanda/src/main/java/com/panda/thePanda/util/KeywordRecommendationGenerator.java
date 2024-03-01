package com.panda.thePanda.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.panda.thePanda.dto.MainAndSubKeywordsDTO;


//키워드를 전달 받아 50자 미만의 키워드를 생성하는 클래스
@Component
public class KeywordRecommendationGenerator {

	 // 추천 키워드 생성 메서드
    public List<String> generateRecommendedKeywords(MainAndSubKeywordsDTO mainAndSubKeywordsDTO) {
        // 추천 키워드를 저장할 리스트 생성
        List<String> recommendedKeywords = new ArrayList<>();
        // 주어진 DTO에서 메인 키워드와 서브 키워드를 추출
        String[] mainKeywords = mainAndSubKeywordsDTO.getMainKeywords();
        String[] subKeywords = mainAndSubKeywordsDTO.getSubKeywords();

        // 메인 키워드를 기반으로 임시 배열을 준비
        String[] tmp = prepareTemporaryArray(mainKeywords);
        // 서브 키워드를 분해하여 리스트로 저장
        List<String> decomposedSubKeywords = decomposeSubKeywords(subKeywords);

        // 새로운 키워드 생성
        generateKeywords(tmp, decomposedSubKeywords, recommendedKeywords);

        // 생성된 추천 키워드 리스트 반환
        return recommendedKeywords;
    }

    // 임시 배열을 생성하는 메서드
    private String[] prepareTemporaryArray(String[] mainKeywords) {
        String[] tmp = new String[mainKeywords.length * 2 + 1];
        // 임시 배열 초기화
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = "";
        }
        // 메인 키워드를 임시 배열에 할당
        for (int i = 0; i < mainKeywords.length; i++) {
            tmp[i + (i + 1)] = mainKeywords[i];
        }
        return tmp;
    }

    // 서브 키워드를 분해하여 리스트로 반환하는 메서드
    private List<String> decomposeSubKeywords(String[] subKeywords) {
        List<String> decomposedKeywords = new ArrayList<>();
        // 서브 키워드를 분해하여 리스트에 추가
        for (int i = 0; i < subKeywords.length; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = i; j < subKeywords.length; j++) {
                temp.append(subKeywords[j]);
                decomposedKeywords.add(temp.toString());
                if (j < subKeywords.length - 1) {
                    temp.append(" ");
                }
            }
        }
        return decomposedKeywords;
    }

    // 새로운 키워드를 생성하는 메서드
    private void generateKeywords(String[] tmp, List<String> decomposedSubKeywords, List<String> recommendedKeywords) {
        // 임시 배열과 분해된 서브 키워드 리스트를 사용하여 새로운 키워드 생성
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].isEmpty()) {
                for (String decomposedSubKeyword : decomposedSubKeywords) {
                    String newKeyword = createNewKeyword(tmp, i, decomposedSubKeyword);
                    addIfUnder50Characters(newKeyword, recommendedKeywords);
                }
            }
        }
    }

    // 새로운 키워드가 50자 이내인 경우에만 리스트에 추가하는 메서드
    private void addIfUnder50Characters(String keyword, List<String> recommendedKeywords) {
        if (keyword.length() <= 50) {
            recommendedKeywords.add(keyword.trim());
        }
    }

    // 새로운 키워드를 생성하는 메서드
    private String createNewKeyword(String[] tmp, int index, String decomposedSubKeyword) {
        tmp[index] = decomposedSubKeyword;
        StringBuilder newKeyword = new StringBuilder();
        for (String key : tmp) {
            if (!key.isEmpty()) {
                newKeyword.append(key).append(" ");
            }
        }
        tmp[index] = "";
        if (newKeyword.length() > 50) {
            newKeyword.setLength(50);
        }
        return newKeyword.toString();
    }


}
