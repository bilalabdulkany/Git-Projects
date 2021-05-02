package com.simpledev;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2	
public class SpringBootWeatherApiApplication {

	public static final Contact DEFAULT_CONTACT = new Contact(
	        "The Weather API", "", "bilal.abdulkany@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
	        "The Weather API", "Get the current weather anywhere in the world", "1.0",
	        "urn:tos", DEFAULT_CONTACT,
	        "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWeatherApiApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
}
