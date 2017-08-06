<%@page import="com.uflowertv.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/${themesName }/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/easyui_icons.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/demo.css">
<script type="text/javascript" src="js/fckeditor/fckeditor.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/my_js.js"></script>
<script type="text/javascript">
	$(function(){
		//处理页面跳转方式
		if (window != top) {
			var user= '<%=user%>';
			if(user == "null"){
				top.location.href = "jsp/redirect.jsp";
			}
		}
	});
</script>
</head>
<body>
	
</body>
</html>