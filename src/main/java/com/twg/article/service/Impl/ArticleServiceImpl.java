package com.twg.article.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twg.article.dao.ArticleDao;
import com.twg.article.model.Article;
import com.twg.article.service.IArticleService;
import com.twg.article.util.PageUtil;


@Service
public class ArticleServiceImpl implements IArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	

	
	public int selectArticleCount() {
		// TODO Auto-generated method stub
		return articleDao.selectArticleCount();
	}

	
	public List<Article> selectArticleByUserID(int id) {
		// TODO Auto-generated method stub
		return articleDao.selectArticleByUserID(id);
	}

	
	public List<Article> getArticleListByUserId(int userId) {
		// TODO Auto-generated method stub
		return articleDao.getArticleListByUserId(userId);
	}

	
	public Article getArticleListById(int id) {
		// TODO Auto-generated method stub
		return articleDao.getArticleListById(id);
	}

	
	public List<Article> selectArticleList() {
		// TODO Auto-generated method stub
		return articleDao.selectArticleList();
	}

	
	public int addArticle(Article article) {
		// TODO Auto-generated method stub
		return articleDao.addArticle(article);
	}

	
	public int updateArticle(Article article) {
		// TODO Auto-generated method stub
		return articleDao.updateArticle(article);
	}

	
	public int deleteArticleByUserId(int userId) {
		// TODO Auto-generated method stub
		return articleDao.deleteArticleByUserId(userId);
	}

	
	public int deleteArticleById(int id) {
		// TODO Auto-generated method stub
		return articleDao.deleteArticleById(id);
	}


	public int selectArticleCountByUser(int userId) {
		// TODO Auto-generated method stub
		return articleDao.selectArticleCountByUser(userId);
	}

	
	public List<Article> pageArticle(PageUtil page) {
		// TODO Auto-generated method stub
		return articleDao.pageArticle(page);
	}


	public List<Article> pageArticleByUser(PageUtil page) {
		// TODO Auto-generated method stub
		return articleDao.pageArticleByUser(page);
	}

	
	public int selectFilterCount(Article article) {
		// TODO Auto-generated method stub
		return articleDao.selectFilterCount(article);
	}

	
	public List<Article> pageFilterArticle(PageUtil page) {
		// TODO Auto-generated method stub
		return articleDao.pageFilterArticle(page);
	}

}
