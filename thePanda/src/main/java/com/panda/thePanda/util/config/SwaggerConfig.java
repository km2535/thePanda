package com.panda.thePanda.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
				.title("더 판다 API") // API의 제목
				.description("더 판다 API 명세서 "
						+ " [ 키워드 검색, 연관 키워드 추출 ]  ")
				.version("1.0.0"); // API의 버전
	}

}
