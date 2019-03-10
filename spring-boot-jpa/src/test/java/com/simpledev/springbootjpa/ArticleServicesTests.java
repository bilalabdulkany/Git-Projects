package com.simpledev.springbootjpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.simpledev.springbootjpa.service.ArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServicesTests {

	@Autowired
	ArticleService articleService;

	@Test
	public void findAllComments() {

		Article article = articleService.findById((long) 1);
		if (article != null) {
			article.setComment(addCommentsToArticle());
			article.getComment().forEach(a -> {
				System.out.println(a.getEmail() + " " + a.getMessage());
			});

		}
		assertNotNull(article);
	}

	@Test
	public void saveArticle() {
		Article article;
		article = articleService.findById((long) 4);
		if (article == null) {
			article = new Article();
			article.setId((long) 4);
			article.setContent("Example Content");
			article.setDate(LocalDate.now());
			article.setEmail("test@gmail.com");
			article.setTitle("Title 1");
			article.setPublished(true);
		}
		Article testArticle = null;
		if (article != null) {
			testArticle = articleService.save(article);
			testArticle.setComment(addCommentsToArticle());
			testArticle.getComment().forEach(a -> {
				System.out.println(a.getId() + " " + a.getMessage() + " by: " + a.getEmail());
			});
		}

		assertNotNull(testArticle);
		assertNotNull(article);
		assertEquals(testArticle.getComment().size(), 2);
	}

	/**
	 * Test case to delete an article, and check if the comments are deleted
	 */
	@Test
	public void deleteArticle() {
		Article article = articleService.findById((long) 4);
		if (article != null) {
			articleService.delete(article);
			article = articleService.findById((long) 4);
			assertNull(article);
		} else
			assertNotNull(article);
	}

	/**
	 * Sample data to add
	 * 
	 * @return List<Comment>
	 */
	private List<Comment> addCommentsToArticle() {
		List<Comment> commentArticleList = new ArrayList<Comment>();
		Comment comment = new Comment();

		comment.setDate(LocalDate.now());
		comment.setEmail("new@email.com");
		comment.setMessage("hi this is great");
		comment.setId(1);
		/*
		 * Here I am adding the second article on the same object created
		 */
		commentArticleList.add(comment);
		comment = new Comment();
		comment.setDate(LocalDate.now());
		comment.setEmail("new1@email.com");
		comment.setMessage("hi this is great too");
		comment.setId(2);
		commentArticleList.add(comment);

		return commentArticleList;
	}
}
