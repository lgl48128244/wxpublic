<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出现错误</title>
<style type="text/css">
body {
	font-family: 'Courgette', cursive;
}

body {
	background: #f3f3e1;
}

.wrap {
	width: 100%;
}

.logo {
	text-align: center;
	margin: 50px 0 0 0;
}

img.img {
	width: 180px;
}

.sub a {
	color: white;
	background: #8F8E8C;
	text-decoration: none;
	padding: 4px 70px;
	font-size: 13px;
	font-family: arial, serif;
	font-weight: bold;
	-webkit-border-radius: 3em;
	-moz-border-radius: .1em;
	-border-radius: .1em;
}

.footer {
	color: #8F8E8C;
	position: absolute;
	right: 10px;
	bottom: 2px;
	font-size: 10px;
}

.footer a {
	color: rgb(228, 146, 162);
}
</style>
</head>
<body>
	<div class="wrap">
		<div class="logo">
			<img src="../images/error-404.jpg" class="img" />
			<div class="sub">
				<p>
					<a href="<%=path %>/login.jsp">Back</a>
				</p>
			</div>
		</div>
	</div>
	<br />
	<h1>
		发现异常:
		<%=(Exception) request.getAttribute("ex")%>
	</h1>
</body>
</html>