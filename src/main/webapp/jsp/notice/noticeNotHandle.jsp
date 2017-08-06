<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>未处理</title>
    <style type="text/css">
		.cc {
		 overflow:hidden;
		 text-overflow:ellipsis;
		 cursor: auto;
		 color: #999999;
		 text-decoration: none;
		}
    </style>
   	<jsp:include page="../util/easyui_util.jsp"></jsp:include>
   	<script type="text/javascript">
   	$(function() {
		initGride('notice/getNoticeHandler.do?type=0');
		
		/**
		 * 查询
		 */
		$('#searchbtn').click(function(){
			$.messager.progress({
				title:"查询进度条",
				text:"正在查询。。。",
				interval:1000
			}); 
			$('#noticeDataGrid').datagrid('load' ,formToObject($('#noticeDataGrid_form')));
		});
		
		/**
		 * 清空
		 */
		$('#clearbtn').click(function(){
			$('#noticeDataGrid_form').form('clear');
			$.messager.progress({
				title:"清空进度条",
				text:"正在清空。。。",
				interval:1000
			}); 
			$('#noticeDataGrid').datagrid('load' ,{});
		});
		//为什么一个输入框的时候回车键不管用？
		$(document).keyup(function(event){
			  if(event.keyCode == 13){
				  $('#noticeDataGrid').datagrid('load' ,formToObject($('#noticeDataGrid_form')));
			  }
		});
	});
   	
  	//显示详情
	function noticeInfo(id,status){
  		window.location.href="notice/toNoticeInfo.do?id="+id+"&status="+status;
	}
	/**
	 * 图片显示
	 */
	function picture_Show(value){
		$('#mydialog').dialog({
			onOpen:function(){
				$.post('${pageContext.request.contextPath}/notice/getImgNameList.do' , {id:value},function(result){
					$('#imgName1').attr('src', result[0]);
					$('#imgName2').attr('src', result[1]);
					$('#imgName3').attr('src', result[2]);
					$('#imgName4').attr('src', result[3]);
					$('#imgName5').attr('src', result[4]);
					$("#a1").attr("href",result[0]);
					$("#a2").attr("href",result[1]);
					$("#a3").attr("href",result[2]);
					$("#a4").attr("href",result[3]);
					$("#a5").attr("href",result[4]);
				});
			}
		});
		$('#mydialog').dialog('open');
		$('#imgName1').attr('src', "");
		$('#imgName2').attr('src', "");
		$('#imgName3').attr('src', "");
		$('#imgName4').attr('src', "");
		$('#imgName5').attr('src', "");
		$("#a1").attr("href","");
		$("#a2").attr("href","");
		$("#a3").attr("href","");
		$("#a4").attr("href","");
		$("#a5").attr("href","");
	 }  
	/**
	 * 初始化列表
	 */
   	function initGride(url) {
		$("#noticeDataGrid").datagrid({
			url : url,
			rownumbers : true,
			singleSelect : true,
			pageList : [ 10, 20, 30 ],
			pagination : true,
			columns : [ [ {
				field : 'wxQuestionType',
				title : '问题类型',
				width : 150,
				align : 'center'
			},{
				field : 'wxUserQuestion',
				title : '问题描述',
				width : 200,
				align : 'center',
				formatter : function(value, row, index) {
					return "<span class=cc><nobr><a title=\""+value+"\">"+value+"</a></nobr></span>";
				}
			}, {
				field : 'createtime',
				title : '时间',
				width : 150,
				align : 'center'
			}, {
				field : 'status',
				title : '状态',
				width : 150,
				align : 'center',
				formatter : function(value, row, index) {
					if(value == 0){
						return "未处理";
					}else{
						return "待跟踪";
					}
				}
			}, {
				field : 'wxUserPicname',
				title : '查看图片',
				width : 150,
				align : 'center',
				formatter : function(value, row, index) {
					 if (value=="" || null == value){
					        return "暂无图片";
				        } else {
				     		return "<a href='javascript:void(0)' onclick='picture_Show(\""+row.id+"\")'> 查看详情</a>";
				      }
				}
			},{
				field : 'opt',
				title : '操作',
				width : 150,
				align : 'center',
				formatter : function(value, row, index) {
					return "<a href='javascript:void(0);' onclick='noticeInfo(\"" + row.id + "\",\"" + row.status + "\")'>查看</a>";
				}
			} ] ],onLoadSuccess:function(){
		    	$.messager.progress('close');
		    }
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
  		<div data-options="region:'north',title:'查询',split:true" style="height:120px;">
			<div align="center">
				<form method="post" id="noticeDataGrid_form">
					<div style="margin-top: 10px;">
						<table>
							<tr>
								<td>问题类型:</td>
								<td><input name="wxQuestionType"/></td>
								<td>问题描述:</td>
								<td><input name="wxUserQuestion"/></td>
							</tr>
						</table>
					</div>
				</form>
				<center>
		 			<span>
		 				<a id="searchbtn" class="easyui-linkbutton">查询</a>&nbsp;&nbsp; 
						<a id="clearbtn" class="easyui-linkbutton">清空</a>
		    		</span>
		 		</center>
			</div>
		</div>
		<div id="mydialog" title="图片列表" modal=true  draggable=false class="easyui-dialog" closed=true style="width:530px;height: 350px;">
			<table align="center" cellpadding="0" cellspacing="8">
				<tr>
					<td>
						<a id="a1" href="" target="_blank"><img id="imgName1" src="" alt="加载失败" style="width: 160px; height: 140px;"></a>
					</td>
					<td>
						<a id="a2" href="" target="_blank"><img id="imgName2" src="" alt="加载失败" style="width: 160px; height: 140px;"></a>
					</td>
					<td>
						<a id="a3" href="" target="_blank"><img id="imgName3" src="" alt="加载失败" style="width: 160px; height: 140px;"></a>
					</td>
				</tr>
				<tr>
					<td>
						<a id="a4" href="" target="_blank"><img id="imgName4" src="" alt="加载失败" style="width: 160px; height: 140px;"></a>
					</td>
					<td>
						<a id="a5" href="" target="_blank"><img id="imgName5" src="" alt="加载失败" style="width: 160px; height: 140px;"></a>
					</td>
				</tr>
			</table>
		</div>
	 <div data-options="region:'center',title:'列表'" style="padding:5px;background:#eee;">
		<div id="tool">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" onclick="choose(0)">未处理</a> 
			&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" onclick="choose(3)">待跟踪</a>
		</div>
		<table id="noticeDataGrid" fit="true" toolbar="#tool">
	</div>
  </body>
</html>
