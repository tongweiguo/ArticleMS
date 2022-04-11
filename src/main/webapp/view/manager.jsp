<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>主页面</title>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="article.ico" type="image/x-icon">

<style type="text/css">
	a:link {color: #FF0000}		/* 未访问的链接 */
	a:visited {color: #00FF00}	/* 已访问的链接 */
	a:hover {color: #FF00FF}	/* 鼠标移动到链接上 */
	a:active {color: #0000FF}	/* 选定的链接 */
	body{
		text-align: center;
		background-image: url("static/images/index.jpg");
	}
</style>
</head>
<body>
	<center>
		${message }
		<h1>主页面</h1>
		<hr>
		<a href="user/getUserList">查看所有作者</a> <a href="article/getArticleList">查看所有文章</a><br />
	</center>
</body>
</html>