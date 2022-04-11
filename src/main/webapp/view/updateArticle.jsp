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
    
    <title>修改文章信息</title>
    
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
			/* position:relative;
			left:400px; */
			text-align:center;
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
	${message }
    	<h1>添加文章</h1><hr>
    	<form action="article/updateArticle" method="post" enctype="multipart/form-data">
    			<input type="hidden" name="id" value="${article.id}">
    		标题:<input type="text" name="title" value="${article.title}"/>
    		<br>
     		作者:<select name="userId">
    							<c:forEach items="${userList}" var="user">
    								<%-- <option value="${user.id}">${user.userName}</option> --%>
     								<c:if test="${article.user.id==user.id}" var="b">
     									<option value="${user.id}" selected="selected">${user.userName}</option>
     								</c:if>
     								<c:if test="${article.user.id!=user.id}">
     									<option value="${user.id}">${user.userName}</option>
     								</c:if>
     								
     							</c:forEach> 
    			</select>
    		<br>
    		内容:<textarea rows="3" cols="23" name="content">${article.content}</textarea><br/>
    		选择文件:<input type="file" name="file"><br/>
    		原文章:<input type="text" name="fileName" value="${article.articleURL}" readonly="readonly"><br/>
    		<br>
    		<input type="submit" value="修改" id="submit">
    	</form>
    	<span onclick="history.back()">返回</span>
  </body>
</html>
