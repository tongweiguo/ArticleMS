package com.twg.article.service;

import java.util.List;

import com.twg.article.model.User;
import com.twg.article.util.PageUtil;


public interface IUserService {
	
	/**
	 *	<查询user表总数>
	 *	@return user表总条数
	 */
	public int countAll();
	/**
	 *	<根据ID查询用户>
	 *	@param 用户ID
	 *	@return 单个用户 User
	 */
	public User selectUserByID(int id);
	/**
	 *	<分页查询user表所有数据>
	 *	@return 所有用户数据 
	 *			List<User>
	 */
	public List<User> pageUsers(PageUtil page);
	/**
	 *	<查询user表所有数据>
	 *	@return 所有用户数据 
	 *			List<User>
	 */
	public List<User> selectUsers();
	/**
	 *	<新增一条用户信息>
	 */
	public int addUser(User user);
	/**
	 *	<更新一条用户数据>
	 */
	public int updateUser(User user);
	/**
	 *	<删除一条用户数据>
	 */
	public int deleteUser(int id);
	/**
	 * <查询指定数据总条数>
	 */
	public int countFromUser(User user);
	/**
	 * <分页查询指定数据>
	 * @param user
	 * @return
	 */
	public List<User> pageFromUser(PageUtil page);
	/**
	 * <根据姓名查询><根据姓名查询>
	 * @param name
	 * @return
	 */
	public List<User> selectUserByName(String name);
}
