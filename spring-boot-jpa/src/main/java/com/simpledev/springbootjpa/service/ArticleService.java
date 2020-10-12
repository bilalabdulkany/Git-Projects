package com.simpledev.springbootjpa.service;

import com.simpledev.springbootjpa.model.Article;

public interface ArticleService {

	Article save(Article article);

	Article findById(Long id);

	void delete(Article article);

}
