<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>已处理</title>
   	<jsp:include page="../util/easyui_util.jsp"></jsp:include>
   	<script type="text/javascript">
   	
   	$(function() {
		initGride('notice/getNoticeHandler.do?type=1');
	});
   	
  	//显示详情
	function noticeInfo(id,status){
		window.location.href="notice/toNoticeInfo.do?id="+id+"&status="+status;
	}
	/**
	 * 初始化列表
	 */
   	function initGride(url) {
		$("#noticeDataGrid").datagrid({
			url : url,
			rownumbers : true,
			singleSelect : true,
			fit:true,
			pageList : [ 10, 20, 30 ],
			pagination : true,
			columns : [ [ {
				field : 'wxQuestionType',
				title : '问题类型',
				width : 80,
				align : 'center'
			},{
				field : 'wxUserQuestion',
				title : '问题描述',
				width : 150,
				align : 'center',
				formatter : function(value, row, index) {
					return "<span class=cc><nobr><a title=\""+value+"\">"+value+"</a></nobr></span>";
				}
			}, {
				field : 'createtime',
				title : '提问时间',
				width : 150,
				align : 'center'
			}, {
				field : 'status',
				title : '状态',
				width : 80,
				align : 'center',
				formatter : function(value, row, index) {
					if(value == 1){
						return "待确认";
					}else{
						return "已解决";
					}
				}
			}, {
				field : 'completetime',
				title : '处理时间',
				width : 150,
				align : 'center',
			}, {
				field : 'replyHuman',
				title : '处理人',
				width : 80,
				align : 'center',
				formatter : function(value, row, index) {
					var name;
					$.ajax({
			            url : "notice/findByUserId.do",
			            type : 'post',
			            dataType : 'json',
			            cache:false,
			            async:false,
			            data : {"userId": value},
			            success : function(data) {
			            	 name = data.uname;
			            }
			        });
					return name;
				}
			}, {
				field : 'replyQuestion',
				title : '回复摘要',
				width : 150,
				align : 'center',
				formatter : function(value, row, index) {
					return "<span class=cc><nobr><a title=\""+value+"\">"+value+"</a></nobr></span>";
				}
			},{
				field : 'opt',
				title : '操作',
				width : 100,
				align : 'center',
				formatter : function(value, row, index) {
					return "<a href='javascript:void(0);' onclick='noticeInfo(\"" + row.id + "\",\"" + row.status + "\")'>查看</a>";
				}
			} ] ]
		});
	}
	
	/**
	 * 查询选项
	 */
	function choose(type){
		var url = "notice/getNoticeHandler.do?type="+type;
		initGride(url);
	}
   	</script>
  </head>
  <body class="easyui-layout">
  	 <div data-options="region:'center',title:'列表'" style="padding:5px;background:#eee;">
		<div id="tool">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" onclick="choose(1)">待确认</a> 
			&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" onclick="choose(4)">已处理</a>
		</div>
		<table id="noticeDataGrid" fit="true" toolbar="#tool">
	</div>
  </body>
</html>
