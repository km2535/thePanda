package com.panda.thePanda.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Service
public class GetAutoCompleteKeyword {
	
	private final WebClient.Builder webClientBuilder;

    public GetAutoCompleteKeyword(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    
    
    public List<String> getAutoCompleteFromNaver(String keyword) {
    	 WebClient webClient = WebClient.builder()
                 .baseUrl("https://shopping.naver.com")
                 .defaultHeader(HttpHeaders.USER_AGENT, "Mozila/5.0")
                 .build();

        Mono<List<String>> keywordsMono = webClient.get()
                .uri("/api/modules/gnb/auto-complete?keyword={keyword}", keyword)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .flatMap(response -> {
                    List<String> keywords = new ArrayList<>();
                    JsonNode itemsNode = response.get("items");
                    JsonNode keywordNode = itemsNode.get(1);
                    if (itemsNode != null) {
                        for (int i = 0; i<keywordNode.size(); i++) {
                           JsonNode keywordItem = keywordNode.get(i).get(0).get(0);
                     
                            if (keywordNode != null) {
                                String keywordText = keywordItem.asText();
                                keywords.add(keywordText);
                            }
                        }
                    }
                    return Mono.just(keywords);
                });
        
        return keywordsMono.block(); 
    }
    public List<String> getAutoCompleteFromCoupang(String keyword) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://www.coupang.com")
                .defaultHeader(HttpHeaders.USER_AGENT, "Mozila/5.0")
                .build();
        Mono<List<String>> keywordsMono = webClient.get()
                .uri("/np/search/autoComplete?callback=jQuery111109935626958629895_1709040176437&keyword={keyword}", keyword)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    String jsonData = extractJsonFromJsonp(response);
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<String> keywords = new ArrayList<>();
                    try {
                        JsonNode jsonNode = objectMapper.readTree(jsonData);

                        for (JsonNode node : jsonNode) {
                            String keywordText = node.get("keyword").asText();
                            keywords.add(keywordText);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Mono.just(keywords);
                });
        
        return keywordsMono.block(); 
    }
    public List<String> getAutoCompleteFromGmarket(String keyword) {
    	
    	WebClient webClient = WebClient.builder()
                 .baseUrl("https://frontapi.gmarket.co.kr")
                 .defaultHeader(HttpHeaders.USER_AGENT, "Mozila/5.0")
                 .build();
    	Mono<List<String>> keywordsMono = webClient.get()
    			.uri("/autocompleteV2/kr/json/{keyword}", keyword)
    			.retrieve()
    			.bodyToMono(JsonNode.class)
    			.flatMap(response -> {
    				List<String> keywords = new ArrayList<>();
    				JsonNode itemsNode = response.get("Data");
    				if (itemsNode != null) {
    					for (int i = 0; i<itemsNode.size(); i++) {
    						JsonNode keywordItem = itemsNode.get(i).get("Keyword");
    						if (itemsNode != null) {
    							String keywordText = keywordItem.asText();
    							keywords.add(keywordText);
    						}
    					}
    				}
    				return Mono.just(keywords);
    			});
    	
    	return keywordsMono.block(); 
    }
    
    //쿠팡 데이터를 가져오기 위한 함수 
    private String extractJsonFromJsonp(String jsonData) {
        int startIndex = jsonData.indexOf('[');
        int endIndex = jsonData.lastIndexOf(']');
        if (startIndex != -1 && endIndex != -1) {
            return jsonData.substring(startIndex, endIndex + 1);
        } else {
            return "";
        }
    }
}
