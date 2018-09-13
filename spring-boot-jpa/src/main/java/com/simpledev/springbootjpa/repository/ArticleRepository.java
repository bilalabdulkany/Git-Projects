package com.simpledev.springbootjpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.simpledev.springbootjpa.model.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article,Long> {

}
