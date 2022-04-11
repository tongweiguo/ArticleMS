package com.twg.article.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


public class UploadUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1005856538553566949L;
	
	private String name;
	private String description;
	private MultipartFile fileName;
	private MultipartFile file2;
	
	public UploadUser(){
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getFileName() {
		return fileName;
	}
	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getFile2() {
		return file2;
	}
	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}
	
	
}
