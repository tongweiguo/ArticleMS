package com.twg.article.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class ArticleFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String userId;
	private String title;
	private String content;
	private String fileName;
	private MultipartFile file;
	
	public ArticleFile(){
		super();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "ArticleFile [id=" + id + ", userId=" + userId + ", title="
				+ title + ", content=" + content + ", fileName=" + fileName
				+ ", file=" + file + "]";
	}
	
	
	
}
