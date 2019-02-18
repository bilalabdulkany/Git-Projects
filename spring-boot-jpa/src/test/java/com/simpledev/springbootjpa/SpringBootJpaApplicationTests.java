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

import ch.qos.logback.core.net.SyslogOutputStream;

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
			article.setComment(addCommentsToArticle());
		}
		if (article != null)
			article.getComment().forEach(a -> {
				System.out.println(a.getEmail() + " " + a.getMessage());
			});
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
		Article testArticle = null;
		if (article != null) {
			testArticle = articleRepository.save(article);
			article.setComment(addCommentsToArticle());

			System.out.println(testArticle.getContent());
			System.out.println("Comments for article " + +article.getId());
			article.getComment().forEach(a -> {
				System.out.println(a.getId() + " " + a.getMessage() + " by: " + a.getEmail());
			});
		}

		assertNotNull(testArticle);
		assertNotNull(article);
	}

	private List<Comment> addCommentsToArticle() {

		List<Comment> commentArticleList = new ArrayList<Comment>();
		Comment comment = new Comment();

		comment.setDate(LocalDate.now());
		comment.setEmail("new@email.com");
		comment.setMessage("hi this is great");
		comment.setId(1);

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
