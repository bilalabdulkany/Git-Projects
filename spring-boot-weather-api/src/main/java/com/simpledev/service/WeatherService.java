package com.simpledev.service;

import org.springframework.stereotype.Service;


public interface WeatherService {

	public String getWeatherByCity(String city,String country);
	public String getWeatherByLatLon(double lat, double lon);
}
