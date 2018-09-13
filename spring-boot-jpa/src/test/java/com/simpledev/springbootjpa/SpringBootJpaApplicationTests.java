package com.simpledev.springbootjpa;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.simpledev.springbootjpa.model.Article;
import com.simpledev.springbootjpa.model.Comment;
import com.simpledev.springbootjpa.repository.ArticleRepository;
import com.simpledev.springbootjpa.repository.CommentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Test
	public void findAllComments() {
		Comment comment = new Comment();
		comment.setDate(LocalDate.now());
		comment.setEmail("new@email.com");
		comment.setMessage("hi this is great");
		Article article=articleRepository.findById((long) 1).orElse(null);
		if(article!=null) {
			comment.setArticle(article);
		}
		
			
		
		List<Comment> comments = commentRepository.findCommentById((long) 1);
		System.out.println(comments);
		assertNotNull(comments);
	}
	
	@Test
	public void findAllArticles() {
		Article article = articleRepository.findById((long) 1).orElse(null);
		System.out.println(article);		
		assertNotNull(article);
	}
	
	
	
	@Test
	public void saveArticle() {
		
		Article article;
		article=articleRepository.findById((long) 4).orElse(null);
		if(article==null) {
			article=new Article();
		article.setId((long)4);
		article.setContent("Example Content");
		article.setDate(LocalDate.now());
		article.setEmail("test@gmail.com");
		article.setTitle("Title 1");
		article.setPublished(true);
			
		
		}
		Article testArticle=articleRepository.save(article);
		assertNotNull(testArticle);
		assertNotNull(article);
	}
	

}
