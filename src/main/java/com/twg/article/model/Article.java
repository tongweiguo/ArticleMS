package com.twg.article.model;

import java.io.Serializable;

public class Article implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8200514492293874940L;
	private int id;
	private User user;
	private String title;
	private String content;
	private String articleURL;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getArticleURL() {
		return articleURL;
	}
	public void setArticleURL(String articleURL) {
		this.articleURL = articleURL;
	}
	public Article(int id, User user, String title, String content, String articleURL) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.articleURL = articleURL;
	}
	public Article() {
		super();
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", user=" + user + ", title=" + title
				+ ", content=" + content + ", articleURL=" + articleURL + "]";
	}
	
	

}
