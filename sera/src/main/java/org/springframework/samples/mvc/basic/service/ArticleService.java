package org.springframework.samples.mvc.basic.service;

import java.util.List;

import org.springframework.samples.mvc.basic.model.Article;

public interface ArticleService {

	public void addArticle(Article article);

	public List<Article> listArticles();
}