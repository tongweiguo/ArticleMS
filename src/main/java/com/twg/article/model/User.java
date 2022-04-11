package com.twg.article.model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -494910816541277401L;
	private int id;
	private String userName;
	private int userAge;
	private String userAddress;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userAge="
				+ userAge + ", userAddress=" + userAddress + "]";
	}
	public User(int id, String userName, int userAge, String userAddress) {
		super();
		this.id = id;
		this.userName = userName;
		this.userAge = userAge;
		this.userAddress = userAddress;
	}
	public User() {
		super();
	}
	

}
