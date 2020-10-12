package com.crossover.techtrial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crossover.techtrial.model.Article;

@RepositoryRestResource(exported = false)
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

	@Query("select sa from Article sa where lower(sa.title) like lower(CONCAT('%',:searchtext,'%')) or lower(sa.content) like lower(CONCAT('%',:searchtext,'%'))")
  List<Article> findTop10ByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(@Param("searchtext")String title);

}
