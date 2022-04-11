package com.twg.article.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "")
public class LoginController {

	/**
	 * <p>登录接口
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index/login")
	public ModelAndView login(HttpServletRequest request,  HttpServletResponse resp,Model model) throws ServletException, IOException {
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		if(name.equals("admin") && pwd.equals("123456")) {
			request.getSession().setAttribute("USERNAME", name);
			return new ModelAndView("manager");
		}
		ModelAndView view = new ModelAndView("login");
		view.addObject("message", "登录失败,用户名或密码错误!");
		return view;
	}
	
	@RequestMapping(value = "/index/toLogin")
	public ModelAndView toLogin(HttpServletRequest request,  HttpServletResponse resp) {
		ModelAndView view = new ModelAndView("login");
		
		return view;
	}
	
	@RequestMapping(value = "/")
	public ModelAndView toMain(HttpServletRequest request,  HttpServletResponse resp) {
		System.out.println("跳转主页");
		ModelAndView view = new ModelAndView("manager");
		return view;
	}
}
