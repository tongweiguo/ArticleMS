<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>下载页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function back(){
			history.back();
		}
	</script>

  </head>
  
  <body>
    <center>
    	<span style="font-size:18px;">${message}</span><br/>
		<c:forEach items="${map}" var="map">
			<c:url value="article/downLoad" var="downUrl">
				<c:param name="fileName" value="${map.key}"></c:param>

			</c:url> 
			${map.value}<br/><br/>
    		<%-- <audio src="music/${map.key}" controls="controls"/> --%>
    		<a href="${downUrl}">下载</a>
			<br />
			
			<br />
		</c:forEach>
		
		<span onclick="history.back()">返回</span>
	</center>
  </body>
</html>
