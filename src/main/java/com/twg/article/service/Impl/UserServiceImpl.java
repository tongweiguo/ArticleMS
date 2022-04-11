package com.twg.article.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twg.article.dao.UserDao;
import com.twg.article.model.User;
import com.twg.article.service.IUserService;
import com.twg.article.util.PageUtil;


@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserDao userDao;
	
	public int countAll() {
		// TODO Auto-generated method stub
		return userDao.countAll();
	}

	
	public User selectUserByID(int id) {
		// TODO Auto-generated method stub
		return userDao.selectUserByID(id);
	}

	
	public List<User> pageUsers(PageUtil page) {
		// TODO Auto-generated method stub
		return userDao.pageUsers(page);
	}


	public List<User> selectUsers() {
		// TODO Auto-generated method stub
		return userDao.selectUsers();
	}


	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}

	
	public int countFromUser(User user) {
		// TODO Auto-generated method stub
		return userDao.countFromUser(user);
	}

	
	public List<User> pageFromUser(PageUtil page) {
		// TODO Auto-generated method stub
		return userDao.pageFromUser(page);
	}

	
	public List<User> selectUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.selectUserByName(name);
	}

}
