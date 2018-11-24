package com.simpledev.springbootjpa;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
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
		Comment comment = null;
		Article article = articleRepository.findById((long) 1).orElse(null);
		if (article != null) {
			comment = new Comment();
				
				comment.setDate(LocalDate.now());
				comment.setEmail("new@email.com");
				comment.setMessage("hi this is great");
				comment.setId(1);
				List<Comment> commentArticleList= new ArrayList<Comment>();
				commentArticleList.add(comment);
				article.setComment(commentArticleList);
			// article.setEmail("aa@aa.com");
			// article.setContent("This is a sample content");
			// article.setPublished(true);
			// https://stackoverflow.com/questions/6164123/org-hibernate-mappingexception-could-not-determine-type-for-java-util-set
			// articleRepository.findById((long) 1).orElse(null);
			// List<Comment> comments = commentRepository.findCommentById((long) 1); TODO:
			// Check the logic
		}
		if (article != null)
			System.out.println(article.getComment());
		else
			System.out.println("list empty");
		assertNotNull(article);
		assertNotNull(comment);

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
		article = articleRepository.findById((long) 4).orElse(null);
		if (article == null) {
			article = new Article();
			article.setId((long) 4);
			article.setContent("Example Content");
			article.setDate(LocalDate.now());
			article.setEmail("test@gmail.com");
			article.setTitle("Title 1");
			article.setPublished(true);

		}
		Article testArticle = articleRepository.save(article);
		assertNotNull(testArticle);
		assertNotNull(article);
	}

}
