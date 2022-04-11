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
    
    <title>作者列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="article.ico" type="image/x-icon">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="static/js/jquery-1.6.4.js"></script>
	<style type="text/css">
		a:link {color: #FF0000}		/* 未访问的链接 */
		a:visited {color: #00FF00}	/* 已访问的链接 */
		a:hover {color: #FF00FF}	/* 鼠标移动到链接上 */
		a:active {color: #0000FF}	/* 选定的链接 */
		body{
			text-align: center;	
			background-image: url("static/images/showUser.jpg");
		}
		a{
			text-decoration: none;
		}
		table{
			/* position:relative;
			left:400px; */
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
		#form_query{
			/* float: right; */
		}
	</style>
	<script type="text/javascript">
		function del(){
			var b = confirm("删除该歌手会删除其下所有歌曲,是否删除?");
			if(b==true){
				return true;
			}else{
				return false;
			}
		}
		
		
		$(document).ready(function(){
  		// 在这里写你的代码...
  			
		});
	</script>
  </head>
  
  <body>
    <center>
    ${message }
    	<h1>作者管理界面</h1><hr>
    	<a href="user/toAddUser">添加作者</a><br/>
    	<p></p>
    	
    	<form action="user/selectFormUser" method="get" id="form_query">
    		根据&nbsp;<select name="name">
    				<option value="id">编号</option>
    				<option value="userName">姓名</option>
    				<option value="userAge">年龄</option>
    				<option value="userAddress">地址</option>
    		   </select>
    		   查询:
    		   <input type="text" name="value">
    		   <input type="submit" value="开始查询"/>
    	</form>
    	<table cellpadding="0" cellspacing="0">
    		<tr>
    			<!-- <th>编号</th> -->
    			<th>姓名</th>
    			<th>年龄</th>
    			<th>住址</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${userList}" var="user">
    		<tr>
    			<%-- <td>${user.id }</td> --%>
    			<td><a href="article/getArticleListByUser?userId=${user.id}" title="查看该作者所有文章">${user.userName }</a></td>
    			<td>${user.userAge }</td>
    			<td>${user.userAddress }</td>
    			<td>
					<a href="user/toUpdateUser?id=${user.id}" >修改</a>
					<a href="user/deleteUserByID?id=${user.id}" onclick="javascript:return del()">删除</a>
				</td>
    		</tr>
    		</c:forEach>
    	</table>
    	
    	第${page.page}/${page.pageSum }页
    	<a href="user/getUserList">首页</a>
    	<a href="user/getUserList?page=${page.page-1 }">上一页</a>
    	<a href="user/getUserList?page=${page.page+1 }">下一页</a>
    	<a href="user/getUserList?page=${page.pageSum}">尾页</a>
    	<a href="<%=basePath%>">返回主页</a>
    </center>	
  </body>
</html>
