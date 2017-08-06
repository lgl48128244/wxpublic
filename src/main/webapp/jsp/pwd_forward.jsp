<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
      <base href="<%=basePath%>">
	  <title>密码修改</title>
	  <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
	<body style="overflow:hidden"> 
	  <form method="post" action="user/forget_password.do" class="loginform">
	    <div id="bg">
	      <div id="logo"></div>
	      <div id="lay">
	        <div id="con">
	          <dl>
	            <dt id="acc">邮箱：</dt>
	            <dd>
	              <label class="label">
	                <input name="email" type="text" dataType="e" errormsg="请输入正确邮箱" nullmsg="请输入正确邮箱" sucmsg="输入正确" />
	              </label>
	            </dd>
	          </dl>
	          <span class="erorr"></span>
	        </div>
	        <div id="bottom">
	          <input type="submit" class="btn" value="发送邮件" />
	        </div>
	      </div>
	    </div>
	  </form>
  	  <script type="text/javascript" src="js/jquery.min.js"></script>
	 <script type="text/javascript" src="js/Validform_min.js"></script>
	  <script type="text/javascript">
	  $(".loginform").Validform({
	    tiptype:3,
	    showAllError:true,
	    ajaxPost:true,
	    callback:function(data){
	      if(data.code==200){
	        window.location.href="jsp/redirect.jsp?message="+data.message;
	      }else{
	    	var datas = data.data;
    	  	if (datas.emialMsg) {
	          $('input[name="email"]').next().removeClass('Validform_right').addClass('Validform_wrong').html(datas.emialMsg);
	        };
	      }
	    }
	  });
	  </script>
	</body>
</html>