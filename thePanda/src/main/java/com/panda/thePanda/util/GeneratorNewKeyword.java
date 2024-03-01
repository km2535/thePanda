package com.panda.thePanda.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GeneratorNewKeyword {
	public List<String> recommandNewKeyword(Map<Integer, List<String>> keywords,
			int keywordLengthLimit ){
		//추천키워드를 담는 list
		List<String> result = new ArrayList<>();
		int count = 0;
		String newProductName = "";
		for(int i = keywords.size(); i>=0; i--) {
			List<String> keyword = null;
			List<String> zeroWord = null;
			if(i == 0) {
				zeroWord = keywords.get(i);
			}
			if(i>0 && keywords.get(i) != null) {
				keyword = keywords.get(i);
			}
			// 중복된 키워드가 있을 경우 
			if(keyword != null) {
				for(int j = 0; j < keywords.get(i).size(); j++) {
					//System.out.println(keyword.get(j) + i);
					newProductName += keyword.get(j) + " ";
					count ++;
					if(count == keywordLengthLimit) {
						result.add(newProductName);
						count = 0;
						newProductName = "";
					}
					
					
				}
			}
			if(zeroWord!=null) {
				for(int j = 0; j < keywords.get(i).size(); j++) {		
						if(result.size() != 0&&newProductName != "" && count != keywordLengthLimit) {
							newProductName +=  zeroWord.get(j) + " ";
							count++;
							if(count == keywordLengthLimit) {
								result.add(newProductName);
								count = 0;
								newProductName = "";
							}
						}
						if(result.size() == 0 &&count != keywordLengthLimit) {
							newProductName +=  zeroWord.get(j) + " ";
							count++;
							if(count == keywordLengthLimit) {
								result.add(newProductName);
								count = 0;
								newProductName = "";
							}
						}
				}
			}
			
		}
		        
		return result;
	}
}
