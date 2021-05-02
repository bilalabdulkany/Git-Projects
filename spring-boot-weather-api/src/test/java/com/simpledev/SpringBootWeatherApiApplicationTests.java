package com.simpledev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;

import com.simpledev.service.WeatherService;

@RunWith(SpringRunner.class)
@RestClientTest(WeatherService.class)
@TestPropertySource(locations="classpath:application-test.properties",properties = "appkey=abc")
//@ActiveProfiles("test")
class SpringBootWeatherApiApplicationTests {

	@Value("${weatherserviceUrl}")
	String url;
	
	private String weatherCityString="{\"coord\":{\"lon\":-3.599,\"lat\":55.1875},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":273.73,\"feels_like\":273.73,\"temp_min\":272.59,\"temp_max\":275.37,\"pressure\":1013,\"humidity\":57},\"visibility\":10000,\"wind\":{\"speed\":1.23,\"deg\":343,\"gust\":1.18},\"clouds\":{\"all\":8},\"dt\":1619922263,\"sys\":{\"type\":3,\"id\":2034958,\"country\":\"GB\",\"sunrise\":1619929862,\"sunset\":1619985092},\"timezone\":3600,\"id\":2643853,\"name\":\"Ae\",\"cod\":200}";
	
	private String weatherLatLonString="{\"coord\":{\"lon\":55.1875,\"lat\":-3.599},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03d\"}],\"base\":\"stations\",\"main\":{\"temp\":302.23,\"feels_like\":305.68,\"temp_min\":302.23,\"temp_max\":302.23,\"pressure\":1009,\"humidity\":68,\"sea_level\":1009,\"grnd_level\":1009},\"visibility\":10000,\"wind\":{\"speed\":2.39,\"deg\":117,\"gust\":2.37},\"clouds\":{\"all\":39},\"dt\":1619925897,\"sys\":{\"country\":\"SC\",\"sunrise\":1619921800,\"sunset\":1619964938},\"timezone\":14400,\"id\":241302,\"name\":\"English River\",\"cod\":200}";
		
	@Autowired
	private WeatherService weatherService;

	@Autowired
	private MockRestServiceServer server;
	
	@Before
	void contextLoads() {
		System.out.println("URL:"+url);
		
	}
	
	@Test
	public void WeatherbyCityTest() {
		this.server.expect(
				requestTo(url + "?q=ae,dxb&APPID=abc"))
				.andRespond(withSuccess(
						new ClassPathResource("test-json/weather-dxb.json"),
						MediaType.APPLICATION_JSON));
		String weather = this.weatherService.getWeatherByCity("ae", "dxb");
		System.out.println(weather);
		assertEquals(weather, weatherCityString);
		this.server.verify();
	}

	@Test
	public void getWeatherForecast() {
		this.server.expect(
				requestTo(url + "?lat=-3.599&lon=55.187&APPID=abc"))
		.andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
				.andRespond(withSuccess(
						new ClassPathResource("test-json/weather-ae.json"),
						MediaType.APPLICATION_JSON));
		String weather=this.weatherService.getWeatherByLatLon(-3.599, 55.187);
		assertEquals(weather,weatherLatLonString);
		this.server.verify();
	}
}
