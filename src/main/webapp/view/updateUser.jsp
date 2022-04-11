<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改页面</title>
    
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
		body{
			text-align: center;
		}
		form{
			text-align:center;
		}
		input{
			margin-top: 10px;
		}
	</style>

  </head>
  
  <body>
    ${message }
    	<h1>修改作者信息</h1><hr>
    	<form action="user/updateUser" method="get">
    		<input type="hidden" name="id" value="${user.id}"/>
    		姓名:&nbsp;&nbsp;<input type="text" name="userName" value="${user.userName}"/>
    		<br>
    		年龄:&nbsp;&nbsp;<input type="text" name="userAge" value="${user.userAge}"/>
    		<br>
    		住址:&nbsp;&nbsp;<input type="text" name="userAddress" value="${user.userAddress}"/>
    		<br>
    		<input type="submit" value="修改" id="submit"><span onclick="history.back()">返回</span>
    	</form>
  </body>
</html>
