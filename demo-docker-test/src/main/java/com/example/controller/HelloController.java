package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class HelloController {
	
	@GetMapping("/hello/{name}")
	public String getName(@PathVariable String name) {
				return "Hello "+name;
	}

}
