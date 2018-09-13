package com.simpledev.springbootjpa.service;

import java.util.List;

import com.simpledev.springbootjpa.model.Comment;

public interface CommentService {

	List<Comment> findComments(Long articleId);
	Comment save(Comment comment);
}
