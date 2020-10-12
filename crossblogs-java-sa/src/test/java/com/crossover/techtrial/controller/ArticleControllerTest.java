package com.crossover.techtrial.controller;

import static org.hamcrest.Matchers.*;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TestContextTransactionUtils;
import org.springframework.test.context.util.TestContextResourceUtils;
import org.springframework.test.web.servlet.MockMvc;

import com.crossover.techtrial.CrossBlogsApplication;
import com.crossover.techtrial.model.Article;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

  @Autowired
  private TestRestTemplate template;

  @Before
  public void setup() throws Exception {

  }

  @Test
  public void contextLoads() {

	  CrossBlogsApplication.main(new String[]{
              "--spring.main.web-environment=false",
              "--spring.autoconfigure.exclude=blahblahblah",
              // Override any other environment properties according to your needs
      });
  }
  
  @Test
  public void testArticleShouldBeCreated() throws Exception {
    HttpEntity<Object> article = getHttpEntity(
        "{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");  
    ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
        Article.class);
    Assert.assertNotNull(resultAsset.getBody().getId()); 
    }
  
  @Test
  public void testArticleInvalidCreated() throws Exception {
    HttpEntity<Object> article = getHttpEntity(
        "{\"email\": \"invaliduser@gmail.com\", \"title\": \"helloError\",\"published\":\"abc\" }");    
    
    ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
        Article.class);
    Assert.assertThat(resultAsset.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    
    
  }

   @Test
   public void testArticleEquals() throws Exception {
    Article article=new Article(); 
    article.setTitle("hello");
    article.setEmail("user1@gmail.com");
    ResponseEntity<Article> resultAsset=template.getForEntity("/articles/1", Article.class);	
  
    Assert.assertNotEquals(article.hashCode(), resultAsset.getBody().hashCode());
   // Assert.assertNotNull(resultAsset.getBody());
  }
@Test
  public void testArticleFindbyId() throws Exception {	
	ResponseEntity<Article> resultAsset=template.getForEntity("/articles/1", Article.class);
	Assert.assertNotNull(resultAsset.getBody().getId());
  }

@Test
public void testArticleFindbyIdNotFound() throws Exception {	
	ResponseEntity<Article> resultAsset=template.getForEntity("/articles/9999999999", Article.class);
	Assert.assertNull(resultAsset.getBody());
}

@Test
public void testdeleteArticleFindbyId() throws Exception {		
	ResponseEntity<Article> resultAsset=template.exchange("/articles/{article-id}",HttpMethod.DELETE, null, Article.class, 48);
	
	Assert.assertThat(resultAsset.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	Assert.assertNull(resultAsset.getBody().getId());
}

@Test
public void testArticleShouldBeUpdated() throws Exception {
  HttpEntity<Object> article = getHttpEntity(
      "{\"email\": \"user2@gmail.com\", \"title\": \"hello\" }");
  ResponseEntity<Article> resultAsset=template.exchange("/articles/{article-id}",HttpMethod.PUT, article, Article.class, 25);
	
  Assert.assertNotNull(resultAsset.getBody().getId());
}

@Test
public void testArticleFindbyText() throws Exception {	
	ResponseEntity<List<Article>> resultAsset=template.exchange("/articles/search?text=10", 
            HttpMethod.GET,null,new ParameterizedTypeReference<List<Article>>() {
    });	
	List<Article> all=resultAsset.getBody();
	Assert.assertNotNull(all);
}

@Test
public void testErrorArticleFindbyId() throws Exception {		
	ResponseEntity<Article> resultAsset=template.getForEntity("/articles/search", Article.class);

	Assert.assertThat(resultAsset.getStatusCode(), is(HttpStatus.BAD_REQUEST));
}
  private HttpEntity<Object> getHttpEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<Object>(body, headers);
  }
}
