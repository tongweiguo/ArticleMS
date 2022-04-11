<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<base href="<%=basePath%>">
<link rel="shortcut icon" href="article.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="static/css/style.css">

<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/vector.js"></script>

<style type="text/css">
	
</style>
</head>
<body>

<div id="container">
	<div id="output">
		<div class="containerT">
		${message }
			<h1>用户登录</h1>
			<form action="index/login" method="post" class="form" id="entry_form">
				<input type="text" name="username" id="entry_name">
				<input type="password" name="password" id="entry_password">
				<button type="submit" id="entry_btn">登录</button>
				<div id="prompt" class="prompt"></div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
     $(function(){
        Victor("container", "output");   //登录背景函数
        $("#entry_name").focus();
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $("#entry_btn").click();
            }
        });
    }); 
</script>
<div style="text-align:center;">

</div>
</body>
</html>