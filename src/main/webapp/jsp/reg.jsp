<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <base href="<%=basePath%>">
	  <title>油菜花微信平台管理中心</title>
	  <link href="css/style.css" rel="stylesheet" type="text/css" />
	  <script type="text/javascript" src="js/jquery.min.js"></script>
	  <script type="text/javascript" src="js/Validform_min.js"></script>
  </head>
<body>
  <form method="post" action="user/reg.do" class="regform">
    <div id="bg">
      <div id="logo"></div>
      <div id="lay">
        <div id="con">
          <dl>
            <dt id="acc">邮箱：</dt>
            <dd>
              <label class="label">
                <input name="email" type="text" datatype="e" errormsg="请输入正确邮箱" nullmsg="请输入正确邮箱" sucmsg="输入正确">
              <span class="Validform_checktip"></span></label>
            </dd>
            <dt id="acc">用户名：</dt>
            <dd>
              <label class="label">
                <input name="uname" type="text" datatype="s5-18" errormsg="请输入5到18个字符的昵称" nullmsg="请输入6到18个字符的昵称" sucmsg="输入正确" class="Validform_error">
              <span class="Validform_checktip Validform_wrong">请输入5到18个字符的昵称</span></label>
            </dd>
            <dt>密码：</dt>
            <dd>
              <label class="label">
                <input name="passwd" type="password" datatype="s6-18" nullmsg="请输入密码" errormsg="密码至少6个字符,最多18个字符！">
              <span class="Validform_checktip Validform_right">通过信息验证！</span></label>
            </dd>
            <dt>确认密码：</dt>
            <dd>
              <label class="label">
                <input name="passwds" type="password" datatype="s6-18" recheck="passwd" nullmsg="请输入密码" errormsg="确认密码不正确">
              <span class="Validform_checktip"></span></label>
            </dd>
          </dl>
        </div>
        <div id="bottom">
          <a href="jsp/login.jsp">返回登录</a>
          <input type="submit" class="btn" value="提交">
        </div>
      </div>
    </div>
  </form>
  <script type="text/javascript">
	  $(".regform").Validform({
	    tiptype:3,
	    showAllError:true,
	    ajaxPost:true,
	    callback:function(data){
	      if(data.code==200){
	        window.location.href="redirect.jsp?message="+data.message;
	      }else{
	        var datas = data.data;
	        if (datas.emialMsg) {
	          $('input[name="email"]').next().removeClass('Validform_right').addClass('Validform_wrong').html(datas.emialMsg);
	        };
	      }
	    }
	  });
  </script>
</body></html>