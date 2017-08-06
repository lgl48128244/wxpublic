<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'previewImage.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	 <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport">
	<script type='text/javascript' src="js/jquery.min.js"></script>  
	<script type="text/javascript"src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> 
	<script type="text/javascript">
	
		$(function(){
			$.ajax({
	            url : "main/jssdk.do",
	            type : 'post',
	            dataType : 'json',
	            contentType : "application/x-www-form-urlencoded; charset=utf-8",
	            data : {
	                'url' : location.href.split('#')[0]
	            },
	            success : function(data) {
					// 微信信息的以及调用的配置  
	                wx.config({
	                    debug : false,
	                    appId : data.appId,
	                    timestamp : data.timestamp,
	                    nonceStr : data.nonceStr,
	                    signature : data.signature,
	                    jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
	                                  'onMenuShareAppMessage', 'onMenuShareQQ',
	                                  'onMenuShareWeibo', 'hideMenuItems',
	                                  'showMenuItems', 'hideAllNonBaseMenuItem',
	                                  'showAllNonBaseMenuItem', 'translateVoice',
	                                  'startRecord', 'stopRecord', 'onRecordEnd',
	                                  'playVoice', 'pauseVoice', 'stopVoice',
	                                  'uploadVoice', 'downloadVoice', 'chooseImage',
	                                  'previewImage', 'uploadImage', 'downloadImage',
	                                  'getNetworkType', 'openLocation', 'getLocation',
	                                  'hideOptionMenu', 'showOptionMenu', 'closeWindow',
	                                  'scanQRCode', 'chooseWXPay',
	                                  'openProductSpecificView', 'addCard', 'chooseCard',
	                                  'openCard' ]
	                });
	            }
	        });
			
			$(document).on('click', '#previewImage img',function(event) {
		        var imgArray = [];
		        var curImageSrc = $(this).attr('src');
		        var oParent = $(this).parent();
		        if (curImageSrc && !oParent.attr('href')) {
		            $('#previewImage img').each(function(index, el) {
		                var itemSrc = $(this).attr('src');
		                imgArray.push(itemSrc);
		            });
		            wx.previewImage({
		                current: curImageSrc,
		                urls: imgArray
		            });
		        }
		    });
		});
		
	</script>
  </head>
  
  <body>
    <div id="previewImage">
		<img src="http://weixin.u-flower.net/wxpublic/user_question/0oSkkgcgG0bhnh8G_pXHDuNCQZncmpVvrPP4ZnlX6Ma_Y-_dTk0dkypE9ogyjqEA8199982476134614051.jpg">
	</div>
  </body>
</html>
