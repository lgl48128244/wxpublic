<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String email = request.getParameter("email");
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
<body style="overflow:hidden">
  <form method="post" action="user/befUpdatePwd.do" class="regform">
  	<input type="hidden" name="email" value="<%=email %>">
    <div id="bg">
      <div id="logo"></div>
      <div id="lay">
        <div id="con">
          <dl>
            <dt>密码：</dt>
            <dd>
              <label class="label">
                <input name="passwd" type="password" datatype="s6-18" nullmsg="请输入密码" errormsg="密码至少6个字符,最多18个字符！" />
              </label>
            </dd>
            <dt>确认密码：</dt>
            <dd>
              <label class="label">
                <input name="passwds" type="password" datatype="s6-18" recheck="passwd" nullmsg="请输入密码" errormsg="确认密码不正确" />
              </label>
            </dd>
          </dl>
        </div>
        <div id="bottom">
          <input type="submit" class="btn" value="重置密码" />
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
        window.location.href="jsp/redirect.jsp?message="+data.message;
      }else{
        var datas = data.data;
        if (datas.pwdMsg) {
	         $('input[name="passwd"]').next().removeClass('Validform_right').addClass('Validform_wrong').html(datas.pwdMsg);
	     };
      }
    }
  });
  </script>
</body>
</html>