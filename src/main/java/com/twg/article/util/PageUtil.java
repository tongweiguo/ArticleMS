package com.twg.article.util;

import com.twg.article.model.Article;
import com.twg.article.model.User;

public class PageUtil {
	private int page;
	private int pageSize;
	private int count;
	private int pageStart;
	private int pageSum;
	private int id;
	private User user;
	private Article article;
	
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		
		if(page >= pageSum){
			page = pageSum;
		}
		if(page <= 0){
			page = 1;
		}
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pagesize) {
		this.pageSize = pagesize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart() {
		pageStart = (page-1)*pageSize;
	}
	public int getPageSum() {
		return pageSum;
	}
	public void setPageSum() {
		pageSum = (count%pageSize==0)?(count/pageSize):(count/pageSize+1);
		if(pageSum<=0){
			pageSum=1;
		}
	}
	
	
	public PageUtil(int page, int pagesize, int count) {
		super();
		this.pageSize = pagesize;
		this.count = count;
		setPageSum();
		setPage(page);
		setPageStart();
		
	}
	public PageUtil() {
	}
	@Override
	public String toString() {
		return "PageUtil [page=" + page + ", pageSize=" + pageSize + ", count="
				+ count + ", pageStart=" + pageStart + ", pageSum=" + pageSum
				+ ", id=" + id + ", user=" + user + ", article=" + article
				+ "]";
	}
	
	
	
	
}
