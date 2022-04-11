<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加页面</title>
    
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
			background-image: url("static/images/30_134337_pagebg.jpg");
		}
		form{
			/* position:relative;
			left:400px; */
			height:200px;
			width:300px;
			text-align:center;
			/* background-image: url("../static/images/13988647_232433461158_2.jpg"); */
		}
		form select{
			margin-top: 10px;
			margin-bottom: 10px;
			width:180px;
			  
		}
		input{
			
			margin-top: 10px;
			width: 180px;
		}
		#submit{
			width: 50px;
		}
	</style>
  </head>
  
  <body>
  <center>
	${message }
    	<h1>添加文章</h1><hr>
    	<form action="article/addArticle" method="post" enctype="multipart/form-data">
    		标题:<input type="text" name="title"/>
    		<br>
    		作者:<select name="userId">
    							<c:forEach items="${userList}" var="user">
    								<option value="${user.id}">${user.userName}</option>
    							</c:forEach>
    						</select>
    		<br>
    		内容:<textarea rows="3" cols="23" name="content"></textarea>
    		<br>
    		选择文件:<input type="file" name="file"><br/>
    		<input type="submit" value="添加" id="submit"><span onclick="history.back()">返回</span>
    	</form>
   </center> 	
  </body>
</html>
