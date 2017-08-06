<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<base href="<%=basePath%>">
<title>问题详情</title>
<jsp:include page="../util/easyui_util.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		//创建ckeditor对象
       /*  var fck = new FCKeditor("replyQuestion");
		fck.ToolbarSet="Basic";
		fck.Width="100%";    
		fck.Height="100%";   
		fck.BasePath = "js/fckeditor/";
		fck.Config["ImageUploadURL"] = "uploadFck.do";
		fck.ReplaceTextarea(); */
		
		//验证规则
		$.extend($.fn.validatebox.defaults.rules, {    
		    minLength: {    
		        validator: function(value, param){    
		            return value.length >= param[0];    
		        },    
		        message: '十五字'   
		    }
		});  
		
		$("#btn").click( function () {
			//var content = FCKeditorAPI.GetInstance("replyQuestion").GetXHTML("true");  
			var check = $("#addForm").form("validate");
			if(!check){
				$("#replyQuestion").focus();
			}else{
				$.ajax({
				   type: "POST",
				   url: "notice/replyQuestion.do",
				   data: {
					   replyQuestion:$("#replyQuestion").val(),
					   noticeId:'${notice.id }',
					   userId:'${sessionScope.user.id }',
					   stauts:'1'
				   },
				   success: function(data){
					  // var editor = FCKeditorAPI.GetInstance("replyQuestion"); 
					 //  editor.EditorDocument.body.innerHTML="";
					   $.messager.alert("提示信息",data.message,"info");
					   setTimeout(function(){
						   window.location.href = "jsp/notice/noticeNotHandle.jsp";
					   }, 1000);
				   },
				   error:function(){
					   $.messager.alert("警告",data.message,"warning");
				   }
				});
			}
		});
		//返回
		$("#back").click(function(){
			window.location.href = "jsp/notice/noticeNotHandle.jsp";
		});
		//转交其它部门
		$("#changeOther").click( function () {
			$.ajax({
			   type: "POST",
			   url: "notice/changeOther.do",
			   data: {
				   noticeId:'${notice.id }',
				   stauts:'3'
			   },
			   success: function(data){
				   $.messager.alert("提示信息",data.message,"info");
				   setTimeout(function(){
					   window.location.href = "jsp/notice/noticeNotHandle.jsp";
				   }, 1000);
			   },
			   error:function(){
				   $.messager.alert("警告",data.message,"warning");
			   }
			});
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',title:'问题详情',split:true" style="height:150px;">
		<div style="margin-top: 15px;margin-left: 190px;">
			提出时间:<input type="text" disabled="disabled" value="${notice.createtime}" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			问题类型:<input type="text" disabled="disabled" value="${notice.wxQuestionType}"/>
		</div>
		<div style="margin-top: 15px;">
			<table align="center" cellpadding="0" cellspacing="5">
				<tr>
					<td >问题描述</td>
				</tr>
				<tr>
					<td>
						<textarea rows="7" cols="100" disabled="disabled">${notice.wxUserQuestion}</textarea>
					</td>
				</tr>
			</table>
		</div>
		<form id="addForm">
			<table align="center" cellpadding="0" cellspacing="5">
				<tr>
					<td >问题回复</td>
				</tr>
				<tr>
					<td>
						<textarea rows="7" cols="100" id="replyQuestion" class="easyui-validatebox" data-options="required:true,validType:'minLength[15]'"></textarea>
					</td>
				</tr>
			</table>
			<div style="margin-left:450px;">
				<a id="btn" href="javascript:;" class="easyui-linkbutton" size="small">提交回复</a> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a id="changeOther" href="javascript:;" class="easyui-linkbutton" size="small">转给其它部门</a> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a id="back" href="javascript:;" class="easyui-linkbutton" size="small">返回</a> 
			</div>
		</form>
	</div>
</body>
</html>