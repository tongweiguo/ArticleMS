<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>失败页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="article.ico" type="image/x-icon">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		a:link {color: #FF0000}		/* 未访问的链接 */
		a:visited {color: #00FF00}	/* 已访问的链接 */
		a:hover {color: #FF00FF}	/* 鼠标移动到链接上 */
		a:active {color: #0000FF}	/* 选定的链接 */
		body{
			text-align: center;
		}
		a{
			text-decoration: none;
		}
	</style>
  </head>
  
  <body>
    <h1>反馈界面</h1><hr/>
    	<h2>${message}</h2>
    	<a href="article/getArticleList">返回所有文章界面</a>
    	<a href="user/getUserList">返回所有作者界面</a>
    	<a href="<%=basePath%>">返回主页</a>
  </body>
</html>
