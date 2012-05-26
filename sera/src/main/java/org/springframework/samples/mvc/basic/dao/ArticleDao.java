package org.springframework.samples.mvc.basic.dao;

import java.util.Date;
import java.util.List;

import org.springframework.samples.mvc.basic.model.Article;


public interface ArticleDao {
	// To Save the article detail
	public void saveArticle ( Article Article );
	
	// To get list of all articles
	public List<Article> listArticles();
}