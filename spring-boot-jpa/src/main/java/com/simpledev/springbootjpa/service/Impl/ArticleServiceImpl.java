package com.simpledev.springbootjpa.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpledev.springbootjpa.model.Article;
import com.simpledev.springbootjpa.repository.ArticleRepository;
import com.simpledev.springbootjpa.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	
	@Override
	public Article save(Article article) {		
		return articleRepository.save(article);
	}
	@Override
	public Article findById(Long id) {		
		return (Article)articleRepository.findById(id).orElse(null);
	}
	@Override
	public void delete(Article article) {
		articleRepository.delete(article);
	}

}
