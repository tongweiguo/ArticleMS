package com.twg.article.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SessionFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 请求的uri
		String url = request.getRequestURI();
		// 项目路径
		String path = request.getContextPath();
		// 静态路径
		String staticResource = request.getContextPath() + "/static/";
		// 会话用户
		Object user = request.getSession().getAttribute("USERNAME");
		//过滤非登录接口
		if(!url.endsWith("index/toLogin") && !url.endsWith("index/login")
				&& !url.startsWith(staticResource) && !url.endsWith(path)) {
			if(user == null) {
				String toLogin = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ path + "/index/toLogin";
				response.sendRedirect(toLogin);
			}else {
				doFilter(request, response, filterChain);
			}
		}else {
			doFilter(request, response, filterChain);
		}
	}

	/**
	 * 判断是否为Ajax请求 <功能详细描述>
	 * 
	 * @param request
	 * @return 是true, 否false
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

}
