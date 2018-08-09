package com.simpledev;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@Test
	public void getHello() throws Exception {
		mvc.perform(get("/hello").contentType(MediaType.ALL)).andExpect(status().isOk())
				.andExpect(content().string("Hello from REST!"));
	}

	@Test
	public void getWelcome() throws Exception {

		mvc.perform(get("/welcome").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk()).andReturn();

	}
}
