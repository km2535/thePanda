package com.panda.thePanda.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

@Component
public class DupulicateKeywordArrange {
	public   TreeMap<Integer, List<String>> dupulicateKeywordArrangeList(List<String> productName){
		

        // 중복 횟수를 저장할 TreeMap (내림차순 정렬을 위해 TreeMap 사용)
        TreeMap<Integer, List<String>> sortedDuplicateIndexMap = new TreeMap<>((o1, o2) -> Integer.compare(o2, o1));

        // 각 키워드의 등장 횟수 세기
        Map<String, Integer> keywordCounts = new HashMap<>();
        for (String item : productName) {
            String[] keywords = item.split("\\s+");
            for (String word : keywords) {
                keywordCounts.put(word, keywordCounts.getOrDefault(word, -1) + 1);
            }
        }

        // 중복 횟수 별로 인덱스에 저장하기
        for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
            String keyword = entry.getKey();
            int count = entry.getValue();
            List<String> keywords = sortedDuplicateIndexMap.getOrDefault(count, new ArrayList<>());
            keywords.add(keyword);
            sortedDuplicateIndexMap.put(count, keywords);
        }
        return sortedDuplicateIndexMap;
	}
}
