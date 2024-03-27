package com.panda.thePanda.service.crawler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataLabTopKeywordCrawler {

	public List<String> crawlTopKeywordByCategory(int category, int page) {
		List<String> result = new ArrayList<String>();
		String referer = "https://datalab.naver.com";
		String jsonData = "";
		LocalDate currentDate = LocalDate.now();
		LocalDate yesterdayDate = currentDate.minusDays(2);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedYesterdayDate = yesterdayDate.format(formatter);
		String formattedcurrentDate = currentDate.format(formatter);
		String url = "https://datalab.naver.com/shoppingInsight/getCategoryKeywordRank.naver?timeUnit=date&cid="
				+ category + "&timeUnit=date&startDate=" + formattedYesterdayDate + "&endDate="
				+ formattedcurrentDate + "&count=20&page=" + page;
		try {
			Connection.Response response = Jsoup.connect(url)
					.header("referer", referer)
					.timeout(10000)
					.ignoreContentType(true)
					.execute();

			if (response.statusCode() == 200) {
				Document document = response.parse();
				Element body = document.body();
				jsonData = body.text();

				try {
					ObjectMapper mapper = new ObjectMapper();

					JsonNode rootNode = mapper.readTree(jsonData);

					JsonNode ranksNode = rootNode.get("ranks");

					if (ranksNode != null && ranksNode.isArray()) {
						for (JsonNode rank : ranksNode) {
							String keyword = rank.get("keyword").asText();
							result.add(keyword);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Failed to fetch data from the website. Status code: " + response.statusCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 탑 키워드 클릭량
	public Map<String, Integer> getTopKeywordOfRate(Integer category, String keyword) {
		LocalDate currentDate = LocalDate.now();
		LocalDate fewDaysAgoDate = currentDate.minusDays(2);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedfewDaysAgoDate = fewDaysAgoDate.format(formatter);
		String formattedcurrentDate = currentDate.format(formatter);

		Map<String, Integer> keywordClickMap = new HashMap<>();
		String url = "https://datalab.naver.com/shoppingInsight/getKeywordClickTrend.naver?startDate="
				+ formattedfewDaysAgoDate + "&endDate=" + formattedcurrentDate + "&timeUnit=date&cid="
				+ category + "&keyword=" + keyword;
		String referer = "https://datalab.naver.com";
		try {
			Connection.Response response = Jsoup.connect(url)
					.header("referer", referer)
					.timeout(10000)
					.ignoreContentType(true)
					.execute();

			if (response.statusCode() == 200) {
				Document document = response.parse();
				String jsonText = document.body().text();
				keywordClickMap = dataParser(jsonText);

			} else {
				System.out.println("Failed to fetch data from the website. Status code: " + response.statusCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return keywordClickMap;
	}

	private static Map<String, Integer> dataParser(String jsonText) {
		Map<String, Integer> keywordClickMap = new HashMap<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonText);

			JsonNode resultNode = rootNode.get("result");
			if (resultNode.isArray()) {
				for (JsonNode keywordNode : resultNode) {
					String title = keywordNode.get("title").asText();
					JsonNode dataNode = keywordNode.get("data");

					if (dataNode.isArray() && dataNode.size() >= 2) {
						double lastValue1 = dataNode.get(dataNode.size() - 2).get("value").asInt();
						double lastValue2 = dataNode.get(dataNode.size() - 1).get("value").asInt();
						double difference = lastValue2 - lastValue1;
						keywordClickMap.put(title, (int) difference);

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keywordClickMap;
	}
}
