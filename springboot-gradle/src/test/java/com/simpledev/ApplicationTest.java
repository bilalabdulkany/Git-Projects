package com.simpledev;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

	@Autowired
	MockMvc mvc;

//	@Test
//	public void getHello() throws Exception {
//		mvc.perform(get("/hello").contentType(MediaType.APPLICATION_JSON))
//
//				.andExpect(status().isOk()).andReturn()
//
//				.getResponse().getContentAsString().equals("Hello from REST!");
//
//	}
//
	@Test

	public void getWelcome() throws Exception {

		mvc.perform(get("/welcome")).andExpect(view().name("home.html"))
				.andExpect(status().isOk()).andExpect(
						content().string("Welcome, coming from the View Controller!"));
		
		System.out.println("---");

	}

}
