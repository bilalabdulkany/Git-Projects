package com.simpledev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpledev.service.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "WeatherController")
@RequestMapping("/weather/")
public class WeatherController {

	@Autowired
	WeatherService service;
	
	@ApiOperation(value = "Get the current weather by country,city - use ISO 3166 country codes	")
	@GetMapping("/city/{country}/{city}")
	public String getCity(
			 @ApiParam(value = "country", required = true, example = "ae")
			@PathVariable String country,
			 @ApiParam(value = "city", required = true, example = "dxb")
			@PathVariable String city){
		System.out.println(city);
		String services =service.getWeatherByCity(country, city);
		return services;
	}
	
	@ApiOperation(value = "Get the current weather by latitude,longitude")
	@GetMapping("/{lat}/{lon}")
	public String getLatLon(
			 @ApiParam(value = "latitude", required = true, example = "-3.599") 
			@PathVariable double lat, 
			@ApiParam(value = "longitude", required = true, example = "55.1875")
			@PathVariable double lon) {
		System.out.println("langitude: "+lat+", longitude:"+ lon);
		String services =service.getWeatherByLatLon(lat, lon);
		return services;
	}
	
}
