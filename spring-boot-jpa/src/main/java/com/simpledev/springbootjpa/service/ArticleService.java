package com.simpledev.springbootjpa.service;

import java.util.List;

import com.simpledev.springbootjpa.model.Article;

public interface ArticleService {

	Article save();

	Article findById(Long id);

	void delete(Long id);

	List<Article> search(String title);

}
