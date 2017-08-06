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
<script type="text/javascript" src="js/clipboard.min.js"></script>
<script type="text/javascript">
	var clipboard = new Clipboard('.btn');
	clipboard.on('success', function(e) {
		var msg = e.trigger.getAttribute('aria-label');
		alert(msg);
	    e.clearSelection();
	});
</script>
<style type="text/css">
	.btn {
			text-transform: uppercase;
			background: rgb(0, 100, 0); color: white;
			padding: 10px; border-radius: 5px;
			cursor: pointer;margin:10px auto;width:100px;text-align:center;
		}
	.btn:hover {background: rgb(0, 75, 0);}
</style>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',title:'问题详情',split:true" style="height:150px;">
		<form id="editForm" method="post" >	
			<table align="center" cellpadding="0" cellspacing="10">
				<tr>
					<td>
						提出时间:
					</td>
					<td>
						<input type="text" disabled="disabled" value="${notice.createtime}" />
					</td>
				</tr>	
				<tr>
					<td>
						问题类型:
					</td>
					<td>
						<input type="text" disabled="disabled" value="${notice.wxQuestionType}"/>
					</td>
				</tr>	
				<tr>
					<td align="right">问题描述:</td>
					<td>
						<textarea rows="7" cols="80" disabled="disabled">${notice.wxUserQuestion}</textarea>
					</td>
				</tr>	
				<tr>
					<td align="right">客服回复:</td>
					<td>
						<textarea rows="7" cols="80" id="replyQuestion" readonly="readonly">${notice.replyQuestion}</textarea>
						<br/>
						<button class="btn" data-clipboard-target="#replyQuestion" aria-label="复制成功！">复制回复</button>
					</td>
				</tr>
			</table>
		</form>	
	</div>
</body>
</html>