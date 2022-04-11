<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文章列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="article.ico" type="image/x-icon">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function del(){
			var b = confirm("是否删除?");
			if(b==true){
				return true;
			}else{
				return false;
			}
		}
	</script>
	
	
	<style type="text/css">
		a:link {color: #FF0000}		/* 未访问的链接 */
		a:visited {color: #00FF00}	/* 已访问的链接 */
		a:hover {color: #FF00FF}	/* 鼠标移动到链接上 */
		a:active {color: #0000FF}	/* 选定的链接 */
		body{
			text-align: center;
			background-image: url("static/images/showArticle.jpg");
		}
		a{
			text-decoration: none;
		}
		table{
			/* position: relative;
			left: 400px; */
			width: 600px;
			height: 400px;
			text-align: center;	
		}
		table tr{
			
			border-top-color: red;
			
		}
		table th{
			height: 100px;
			background-color: gray;
		}
	</style>

  </head>
  
  <body>
  <center>
    ${message }
    <h1>文章管理界面</h1>
    <hr/>
    <a href="article/toAddArticle">添加文章</a>
    <p></p>
    <form action="article/getFilterArticle" method="get" id="form_query">
    		根据&nbsp;
    			<select name="name">
    				<c:if test="${name=='id'}">
    					<option value="id" selected="selected">编号</option>
	    				<option value="title">标题</option>
    					<option value="user">作者</option>
    					<option value="content">内容</option>
    				</c:if>
    				<c:if test="${name=='title'}">
    					<option value="id">编号</option>
	    				<option value="title" selected="selected">标题</option>
    					<option value="user">作者</option>
    					<option value="content">内容</option>
    				</c:if>
    				<c:if test="${name=='user'}">
    					<option value="id">编号</option>
	    				<option value="title">标题</option>
    					<option value="user" selected="selected">作者</option>
    					<option value="content">内容</option>
    				</c:if>
    				<c:if test="${name=='content'}">
    					<option value="id">编号</option>
	    				<option value="title">标题</option>
    					<option value="user">作者</option>
    					<option value="content" selected="selected">内容</option>
    				</c:if>
    		    </select>
    		   查询:
    		   <input type="text" name="value" value="${value}">
    		   <input type="submit" value="开始查询"/>
    	</form>
    <table cellpadding="0" cellspacing="0">
    		<tr>
    			<!-- <th>歌曲ID</th> -->
    			<th>标题</th>
    			<th>内容</th>
    			<th>作者</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${articleList}" var="article">
    		<tr>
    			<%-- <td>${article.id }</td> --%>
    			<td><a href="article/toDownload?fileName=${article.articleURL}" title="点击下载该歌曲">${article.title }</a></td>
    			<td>${article.content }</td>
    			<td>${article.user.userName }</td>
    			<td>
					<a href="article/toUpdateArticle?id=${article.id}" >修改</a>
					<a href="article/deleteArticleByID?id=${article.id}" onclick="javascript:return del()">删除</a>
				</td>
    		</tr>
    		</c:forEach>
    	</table>
  		  第${page.page}/${page.pageSum }页
    	<a href="article/getFilterArticle?&name=${name}&value=${value}">首页</a>
    	<a href="article/getFilterArticle?page=${page.page-1 }&name=${name}&value=${value}">上一页</a>
    	<a href="article/getFilterArticle?page=${page.page+1 }&name=${name}&value=${value}">下一页</a>
    	<a href="article/getFilterArticle?page=${page.pageSum}&name=${name}&value=${value}">尾页</a>
    	<a href="article/getArticleList">返回所有文章</a>
    	<p>查询到${count}条</p>
   </center> 	
  </body>
</html>
