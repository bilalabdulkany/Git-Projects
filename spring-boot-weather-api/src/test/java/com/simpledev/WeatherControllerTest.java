package com.simpledev;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.simpledev.controller.WeatherController;
import com.simpledev.service.WeatherService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WeatherController.class)
public class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService service;

	private String weatherCityString = "{\"coord\":{\"lon\":-3.599,\"lat\":55.1875},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":273.73,\"feels_like\":273.73,\"temp_min\":272.59,\"temp_max\":275.37,\"pressure\":1013,\"humidity\":57},\"visibility\":10000,\"wind\":{\"speed\":1.23,\"deg\":343,\"gust\":1.18},\"clouds\":{\"all\":8},\"dt\":1619922263,\"sys\":{\"type\":3,\"id\":2034958,\"country\":\"GB\",\"sunrise\":1619929862,\"sunset\":1619985092},\"timezone\":3600,\"id\":2643853,\"name\":\"Ae\",\"cod\":200}";

	private String weatherLatLonString = "{\"coord\":{\"lon\":55.1875,\"lat\":-3.599},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03d\"}],\"base\":\"stations\",\"main\":{\"temp\":302.23,\"feels_like\":305.68,\"temp_min\":302.23,\"temp_max\":302.23,\"pressure\":1009,\"humidity\":68,\"sea_level\":1009,\"grnd_level\":1009},\"visibility\":10000,\"wind\":{\"speed\":2.39,\"deg\":117,\"gust\":2.37},\"clouds\":{\"all\":39},\"dt\":1619925897,\"sys\":{\"country\":\"SC\",\"sunrise\":1619921800,\"sunset\":1619964938},\"timezone\":14400,\"id\":241302,\"name\":\"English River\",\"cod\":200}";

	@Test
	public void retrieveWeatherByCity() throws Exception {

		Mockito.when(service.getWeatherByCity(Mockito.anyString(), Mockito.anyString())).thenReturn(weatherCityString);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather/city/ae/dxb")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		JSONAssert.assertEquals(weatherCityString, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void retrieveWeatherByLatLon() throws Exception {

		Mockito.when(service.getWeatherByLatLon(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(weatherLatLonString);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather/3.44/54.44")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		JSONAssert.assertEquals(weatherLatLonString, result.getResponse().getContentAsString(), false);
	}
	
}
