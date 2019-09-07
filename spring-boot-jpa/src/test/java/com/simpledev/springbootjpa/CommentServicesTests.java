package com.simpledev.springbootjpa;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.simpledev.springbootjpa.model.Article;
import com.simpledev.springbootjpa.model.Comment;
import com.simpledev.springbootjpa.service.ArticleService;
import com.simpledev.springbootjpa.service.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServicesTests {

	@Autowired
	CommentService commentService;

	@Autowired
	ArticleService articleService;

	
	//@Test
	public void findCommentsforArticle() {
		Article article = articleService.findById((long) 1);
		List<Comment> commentList=article.getComment();
		int i=commentList.size();
		System.out.println(i+" bbbb");
		assertEquals(commentList.size(),1);		
	}

	@Test
	public void saveCommentToArticle() {
		Article article = articleService.findById((long) 1);
		Comment newComment=new Comment();
		newComment.setDate(LocalDate.now());
		newComment.setEmail("jill@newmail.com");
		newComment.setMessage("Hi, I like this article very much!");
		newComment.setArticle(article);
		newComment=commentService.save(newComment);
		article.getComment().add(newComment);
		articleService.save(article);
		//This doesn't work
		//TODO how to save a comment for that article?
				//assertEquals(article.getComment().size(), 1);
		assertEquals(article.getComment().get(1).getEmail(),"jill@newmail.com");
	}

	/**
	 * Test case to delete an article, and check if the comments are deleted
	 */
	
	public void deleteComment() {
		
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
