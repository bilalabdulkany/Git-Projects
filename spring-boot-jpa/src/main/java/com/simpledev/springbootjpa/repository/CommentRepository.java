package com.simpledev.springbootjpa.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.simpledev.springbootjpa.model.Comment;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

	List<Comment> findCommentById(Long Id);
}
