package com.twg.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twg.article.model.User;
import com.twg.article.service.Impl.ArticleServiceImpl;
import com.twg.article.service.Impl.UserServiceImpl;
import com.twg.article.util.PageUtil;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired	
	private UserServiceImpl userService;
	@SuppressWarnings("unused")
	@Autowired
	private ArticleServiceImpl articleService;
	
	
	/**
	 * <分页查询所有user>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getUserList")
	public String getUserList(HttpServletRequest request,Model model){
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "开始分页查询.");
		System.out.println("开始分页查询");
		String p = request.getParameter("page");
		int page = 1;
		if(p != null){
			page = Integer.parseInt(p);
		}else{
			page = 1;
		}
		int count = userService.countAll();
		PageUtil pu = new PageUtil(page, 3, count); 
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "分页模型:" + pu);
		//分页查询
		List<User> userList = userService.pageUsers(pu);
		//查询所有
		//List<User> userList = userService.pageUsers(pu);
		model.addAttribute("userList", userList);
		model.addAttribute("page", pu);
		return "showUser";
	}
	/**
	 * <跳转添加user页面>
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "addUser";
	}
	/**
	 * <添加一个user>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addUser" ,produces="text/html;charset=UTF-8")
	public String addUser(HttpServletRequest request, Model model){
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "开始添加作者.");
		String name = request.getParameter("userName");
		String address = request.getParameter("userAddress");
		String age = request.getParameter("userAge");
		/*try {
			name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			address = new String(address.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "");
		User user = new User();
		if(age!=null && age!=""){
			try{
				user.setUserAge(Integer.parseInt(age));
			}catch(Exception e){
				System.out.println("年龄格式不规范!");
			}
		}
		user.setUserName(name);
		user.setUserAddress(address);
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "需要添加的作者:" + user);
		int i = userService.addUser(user);
		//int i=0;
		if(i > 0){
			model.addAttribute("message","添加成功");
			//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "添加成功");
			return "forward:/user/getUserList";
			//return "redirect:/user/getUserList";
		}else{
			model.addAttribute("message", "添加失败,请重新添加!");
			//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "添加失败");
			return "addUser";
		}
	}
	/**
	 * <跳转修改user页面>
	 * @return
	 */
	@RequestMapping("/toUpdateUser")
	public String toUpdateUser(HttpServletRequest request,Model model){
		//获取需要修改的id
		String id = request.getParameter("id");
		User user = userService.selectUserByID(Integer.parseInt(id));
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "要修改的" + user);
		model.addAttribute("user", user);
		return "updateUser";
	}
	/**
	 * <修改user>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(HttpServletRequest request, Model model){
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "开始修改作者信息.");
		//获取前台传过来的属性
		String name = request.getParameter("userName");
		String address = request.getParameter("userAddress");
		
		String id = request.getParameter("id");
		String age = request.getParameter("userAge");
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setUserName(name);
		if(age!=null && age!=""){
			try{
				user.setUserAge(Integer.parseInt(age));
			}catch(Exception e){
				System.out.println("年龄格式不规范!");
			}
		}
		user.setUserAddress(address);
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "修改后的信息:" + user);
		//执行数据库操作
		int i = userService.updateUser(user);
		if(i > 0){
			model.addAttribute("message","修改成功");
			System.out.println("修改成功");
			return "forward:/user/getUserList";
			//return "redirect:/user/getUserList";
		}else{
			User user2 = userService.selectUserByID(Integer.parseInt(id));
			model.addAttribute("message", "修改失败,请重新修改!");
			model.addAttribute("user", user2);
			
			return "updateUser";
		}
	}
	/**
	 * <根据id删除user>
	 * @return
	 */
	@RequestMapping("/deleteUserByID")
	public String deleteUserByID(String id, Model model){
		System.out.println("要删除的id:" + id);
		int i = userService.deleteUser(Integer.parseInt(id));
		if(i > 0){
			model.addAttribute("message", "删除成功!");
			System.out.println("删除成功");
			return "forward:/user/getUserList";
			//return "redirect:/user/getUserList";
		}else{
			model.addAttribute("message", "删除失败!");
			System.out.println("删除失败!");
			return "forward:/user/getUserList";
		}
		//model.addAttribute("message", "测试阶段,不做删除操作!");
		//return "forward:/user/getUserList"; 
	}
	/**
	 * <根据关键字模糊查询><根据关键字模糊查询>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectFormUser")
	public String selectFormUser(HttpServletRequest request, Model model){
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "开始模糊查询.");
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String page = request.getParameter("page");
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "根据" + name + "查询:" + value);
		int i = 1;
		if(page != null && page != ""){
			i = Integer.parseInt(page);
		}
		User user = new User();
		if(value!=null && ""!=value){
			if("id".equalsIgnoreCase(name)){
				
					try{
						user.setId(Integer.parseInt(value));
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("id格式异常");
					}
				
				
			}else if("userName".equalsIgnoreCase(name)){
				user.setUserName(value);
			}else if("userAge".equalsIgnoreCase(name)){
				
					try{
						user.setUserAge(Integer.parseInt(value));
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("年龄格式不规范");
					}
				
			}else if("userAddress".equalsIgnoreCase(name)){
				user.setUserAddress(value);
			}
		}
		int count = userService.countFromUser(user);
		PageUtil pu = new PageUtil(i, 3, count);
		pu.setUser(user);
		List<User> list = userService.pageFromUser(pu);
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "模糊查询结果" + list);
		//System.out.println(user);
		
		model.addAttribute("userList", list);
		model.addAttribute("name", name);
		model.addAttribute("value", value);
		model.addAttribute("page", pu);
		return "showFromUser";
	}
}
