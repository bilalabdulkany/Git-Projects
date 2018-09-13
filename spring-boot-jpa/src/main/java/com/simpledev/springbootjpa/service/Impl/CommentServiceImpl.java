package com.simpledev.springbootjpa.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpledev.springbootjpa.model.Comment;
import com.simpledev.springbootjpa.repository.CommentRepository;
import com.simpledev.springbootjpa.service.CommentService;



@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public List<Comment> findComments(Long articleId) {		
		return commentRepository.findCommentById(articleId);
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepository.save(comment);
	}

}
