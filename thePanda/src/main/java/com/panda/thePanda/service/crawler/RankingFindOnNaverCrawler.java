package com.panda.thePanda.service.crawler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class RankingFindOnNaverCrawler {

    @Autowired
    private HttpSession session;

    public Map<String, String> RankingFindBynvMid(String nvMid, String kWord) {
        @SuppressWarnings("unchecked")
        Map<String, String> productDetail = (Map<String, String>) session.getAttribute("productNaverDetail");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (productDetail != null) {
            if (productDetail.size() > 0) {
                LocalDateTime dateTime = LocalDateTime.parse(productDetail.get("lastUpdate"), formatter);
                if (!productDetail.get("productId").equals(nvMid)
                        || !productDetail.get("searchKeyword").equals(kWord)
                        || currentDateTime.compareTo(dateTime) > 1) {
                    // pName과 세션의 productName이 다를 경우 크롤링 실행
                    productDetail = executeCrawlingBynvMid(nvMid, kWord);
                }
            } else {
                // 세션이 비어있음
                session.invalidate();
            }
        } else {
            // 세션이 없을 경우 크롤링 실행
            productDetail = executeCrawlingBynvMid(nvMid, kWord);
        }
        // System.out.println(productDetail.get("keyword"));
        return productDetail;
    }

    private Map<String, String> executeCrawlingBynvMid(String nvMid, String kWord) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver driver = new ChromeDriver(options);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, String> productDetail = new HashMap<>();
        try {
            int cnt = 0;
            int page = 1;
            boolean hasNextPage = true;
            while (page <= 5 && hasNextPage) { // 5페이지까지 검색
                String url = "https://search.shopping.naver.com/search/all?adQuery=" + kWord + "&origQuery="
                        + kWord + "&pagingIndex=" + page + "&pagingSize=40&productSet=total&query=" + kWord
                        + "&sort=rel&viewType=image";
                driver.get(url);
                driver.manage().timeouts();

                // 스크롤 다운
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

                // 상품 목록 가져오기
                WebElement productList = driver.findElement(By.className("imgList_img_list__vbdJ0"));
                List<WebElement> products = productList.findElements(By.className("product_list_item__Y4XcD"));
                // 각 상품별 정보 가져오기
                for (int i = 0; i < products.size(); i++) {
                    cnt++;
                    WebElement product = products.get(i);
                    // 상품명 가져오기
                    String productName = product.findElement(By.className("product_link__W2i_v")).getText();
                    String productUrl = product.findElement(By.className("product_link__W2i_v")).getAttribute("href");
                    String nvMidinHtml = product.findElement(By.className("product_link__W2i_v"))
                            .getAttribute("data-i");
                    String imgUrl = product.findElement(By.xpath("//a[contains(@class, 'thumbnail_thumb__Bxb6Z')]/img"))
                            .getAttribute("src");

                    String price = product.findElement(By.cssSelector(".price_num__S2p_v > em")).getText();
                    String delivery = product.findElement(By.className("price_delivery__yw_We")).getText();
                    String reviewCount = product.findElement(By.className("product_num__qLoWR")).getText();
                    // 동일한 상품명이 내가 찾는 상품명과 일치하는지 확인
                    if (nvMidinHtml.equals(nvMid)) {
                        // System.out.println(productNames.size());
                        // 일치하는 상품명을 리스트에 추가
                        // 상품의 위치를 응답 맵에 추가
                        productDetail.put("searchKeyword", kWord);
                        productDetail.put("productName", productName);
                        productDetail.put("productId", nvMidinHtml);
                        productDetail.put("vendorId", "");
                        productDetail.put("price", price);
                        productDetail.put("productUrl", productUrl);
                        productDetail.put("imgUrl", imgUrl);

                        productDetail.put("deliveryInfo", "");
                        productDetail.put("deliveryPrice", delivery);

                        productDetail.put("lastUpdate", formattedDateTime);
                        productDetail.put("reviewCount", reviewCount);

                        productDetail.put("rewardInfo", "");
                        productDetail.put("ranking", cnt + "");
                        productDetail.put("type", "naver");
                        break;
                    }
                }
                if (productDetail.isEmpty()) {
                    page++;
                } else {
                    page++;
                    break;
                }

            }
            // 세션에 데이터 저장
            if (productDetail.size() > 0) {
                session.setAttribute("productNaverDetail", productDetail);
                // 세션 만료시간 설정 (1시간)
                session.setMaxInactiveInterval(60 * 60);
            } else {
                productDetail.put("warnning", "검색 결과가 없습니다.");
                session.setAttribute("productNaverDetail", productDetail);
                // 세션 만료시간 설정 (30분)
                session.setMaxInactiveInterval(60 * 30);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // WebDriver 종료
            driver.quit();
        }
        return productDetail;
    }

}
