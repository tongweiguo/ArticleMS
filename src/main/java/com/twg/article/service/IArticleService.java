package com.twg.article.service;

import java.util.List;

import com.twg.article.model.Article;
import com.twg.article.util.PageUtil;

public interface IArticleService {

	/**
	 * <查询Article表总条数>
	 * @return
	 */
	public int selectArticleCount();
	/**
	 * <根据作者id查询文章总条数>
	 * @param userId
	 * @return
	 */
	public int selectArticleCountByUser(int userId);
	/**
	 * <根据userID查询Article>
	 * @param i userID 
	 * @return Article集合
	 */
	public List<Article> selectArticleByUserID(int id);
	/**
	 * <根据userID查询Article>
	 * @param i userID 
	 * @return Article集合
	 */
	public List<Article> getArticleListByUserId(int userId);
	/**
	 * <根据Article自己的ID查询Article>
	 * @param i userID 
	 * @return Article集合
	 */
	public Article getArticleListById(int id);
	/**
	 * <查询所有Article>
	 *  
	 * @return Article集合
	 */
	public List<Article> selectArticleList();
	/**
	 * <新增一条Article>
	 * @param Article
	 * @return 
	 */
	public int addArticle(Article article);
	/**
	 * <根据ID更新一条数据>
	 * @param Article
	 */
	 public int updateArticle(Article article);
	 /**
	  * <根据userID删除Article>
	  *	@param userID 
	  */
	 public int deleteArticleByUserId(int userId);
	 /**
	  * <根据ID删除Article>
	  *	@param userID 
	  */
	 public int deleteArticleById(int id);
	 /**
	  * <分页查询所有文章>
	  * @param page
	  * @return
	  */
	 public List<Article> pageArticle(PageUtil page);
	 /**
	  * <根据作者id分页查询>
	  * @param page
	  * @return
	  */
	 public List<Article> pageArticleByUser(PageUtil page);
	 /**
	  * <查询筛选过后的总条数>
	  * @param article
	  * @return
	  */
	 public int selectFilterCount(Article article);
	 /**
	  * <根据过滤分页查询>
	  * @param page
	  * @return
	  */
	 public List<Article> pageFilterArticle(PageUtil page);
}
