package com.simpledev.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {


	private final RestTemplate restTemplate;

	@Value("${appkey}")
	private String appKey;
	
	@Value("${weatherserviceUrlCity}")
	private String WEATHER_URL;
	
	@Value("${weatherserviceUrlLatLon}")
	private String WEATHER_LANLON_URL;
	
	public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate= restTemplateBuilder.build();
	}
	@Cacheable("weathercity")
	public String getWeatherByCity(String city, String country) {
		String weatherdata=null;
		try {
				
			URI url = new org.springframework.web.util.UriTemplate(WEATHER_URL).expand(city, country,appKey);			
			weatherdata = invoke(url,String.class);

		} catch (Exception e) {
			System.out.println(e);
		}

		return weatherdata;
	}
	@Cacheable("weatherlatlon")
	public String getWeatherByLatLon(double lat, double lon) {
		String weatherdata=null;
		try {	
			URI url = new org.springframework.web.util.UriTemplate(WEATHER_LANLON_URL).expand(lat, lon,appKey);			
			weatherdata = invoke(url,String.class);

		} catch (Exception e) {
			System.out.println(e);
		}

		return weatherdata;

	}
	private <T> T invoke(URI url, Class<T> responseType) {
		System.out.println("Calling services");
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate
				.exchange(request, responseType);
		return exchange.getBody();
	}
}
