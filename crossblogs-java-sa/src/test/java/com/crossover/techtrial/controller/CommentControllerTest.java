package com.crossover.techtrial.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

	@Autowired
	  private TestRestTemplate template;

	  @Before
	  public void setup() throws Exception {

	  }
	  
	  @Test
	  public void testCommentShouldBeCreated() throws Exception {
	    HttpEntity<Object> article = getHttpEntity(
	        "{\"email\": \"user1user2@gmail.com\", \"message\": \"testing message\" ,\"date\":\""+LocalDateTime.now()+"\"}");
	    ResponseEntity<Comment> resultAsset = template.postForEntity("/articles/6/comments", article,
	    		Comment.class);
	    Assert.assertNotNull(resultAsset.getBody().getId());
	  }
	  
	  @Test
	  public void testCommentFindbyId() throws Exception {	
		ResponseEntity<List<Comment>> resultAsset=template.exchange("/articles/6/comments", HttpMethod.GET,null,new ParameterizedTypeReference<List<Comment>>() {
	    });	
		
		Assert.assertNotNull(resultAsset.getBody());
	  }
	  
	  private HttpEntity<Object> getHttpEntity(Object body) {
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    return new HttpEntity<Object>(body, headers);
		  }
}
