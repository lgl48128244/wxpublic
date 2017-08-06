/**构建页面通用组件,创建命名空间**/

/**
 * 全局变量
 */
var BASE_PROJECT = "";
var curWwwPath=window.document.location.href;
if(curWwwPath.indexOf("wxpublic")>0)
	BASE_PROJECT = "/wxpublic";
var BASE_URL = BASE_PROJECT+"/html/";



/**
 * 命名空间的声明
 */
Namespace = new Object();
// 全局对象仅仅存在register函数，参数为名称空间全路径，如"Grandsoft.GEA"
Namespace.register = function(fullNS) {
	// 将命名空间切成N部分, 比如Grandsoft、GEA等
	var nsArray = fullNS.split('.');
	var sEval = "";
	var sNS = "";
	for ( var i = 0; i < nsArray.length; i++) {
		if (i != 0)
			sNS += ".";
		sNS += nsArray[i];
		// 依次创建构造命名空间对象（假如不存在的话）的语句
		// 比如先创建Grandsoft，然后创建Grandsoft.GEA，依次下去
		sEval += "if (typeof(" + sNS + ") == 'undefined') " + sNS
				+ " = new Object();"
	}
	if (sEval != "")
		eval(sEval);
};

/**
 * 注册命名空间
 * 
 */
Namespace.register("mobile.base");
Namespace.register("mobile.util");

/**
 * mobile.base--------------------------------------------------------
 * 基础包
 */

/**
 * mobile.base.security
 * 对需要用户属性时做安全检测
 * 并提供security.userId  security.uuid
 * ------------------------------------------------------
 */
mobile.base.security = function (){
	this._BM = new mobile.base.Main();
};
mobile.base.security.prototype.valdate = function (){
	if(this.actionTime==undefined){
		this.actionTime = new Date().getTime();
	}else{
		if(new Date().getTime()-this.actionTime>6*1000){
			//超时重新检查
			this.actionTime = new Date().getTime();
			this.userId = undefined;
			this.uuid = undefined;
		}
	}
	
	if(this.userId!=undefined&&this.uuid!=undefined)
		return true;
	
	var uinfo = this._BM.getUserInfo("2.0");
	var rs = false;
	if(uinfo=="-1"){
		//alert("请先登录");
		rs = false;
	}else{
		this.userId = uinfo.split(";")[0];
		this.uuid = uinfo.split(";")[1];
		rs = true;
	}
	return rs;
};
mobile.base.security.prototype.init = function(){
	this.userId = undefined;
	this.uuid= undefined;
};
mobile.base.security.prototype.login = function(redirect){
	location.href=BASE_URL+"index.html?redirect="+encodeURIComponent(encodeURIComponent(redirect));
};
/**
 * ------------------------------------------------------
 */

/**
 * 在mobile.base命名空间里面声明类Main
 */
mobile.base.Main = function() {
	this.location = window.location.href.toString();
};
/**
 * 全局变量
 */
mobile.base.Main.prototype.ajaxPrm = undefined;
mobile.base.Main.prototype.location = undefined;

// 初始化AJAX参数initAjax() buildAjax*()
mobile.base.Main.prototype.initAjax = function() {
	this.ajaxPrm = new Object();
	this.ajaxPrm.type = "post";
	this.ajaxPrm.contentType = "application/json;charset=UTF-8";
	this.ajaxPrm.dataType = 'text';
	this.ajaxPrm.success = mobile.base.Main.valdateData;
	this.ajaxPrm.timeout = 15000;
	this.ajaxPrm.complete = function(request,status){
	    var __msg = new mobile.base.messager();
		if(status=='timeout'||status=='error'){
			//超时,status还有success,error等值的情况
			request.abort();
			__msg.closeAll();
		    __msg.prompt("请求超时，请稍后重试", 2000);
		}
	};
};
//通用验证ajax回调结果函数
mobile.base.Main.prototype.valdateData = function(data) {
	data = $.parseJSON(data);
	var message = new mobile.base.messager();
	if (data.errorcode == "-1") {
		alert(data.errorinfo);
		return undefined;
	} else {
		return data;
	}
};
/**
 * 显示一个提示框
 */
mobile.base.messager = function(){
};
/**
 * 创建一个url为front/index.shtml的ajax对象
 */
mobile.base.Main.prototype.buildAjax1 = function() {
	this.initAjax();
	this.ajaxPrm.url = BASE_PROJECT+"/front/index.shtml";
};

mobile.base.Main.prototype.setAjaxData = function(prm){
	var message = new mobile.base.messager();
	if(prm==undefined){
		message.alert("数据异常");
	}else{
		this.ajaxPrm.data = JSON.stringify(prm);
	}
};

// 获取URL参数
mobile.base.Main.prototype.getQueryString = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

$(function(){
	$(".gobackBtn").click(function(){
		history.go(-1);							   
	});		   
});
//序列化
(function($) {
	$.fn.extend({
		serializeObject : function() {
			if (this.length > 1) {
				return false;
			}
			var arr = this.serializeArray();
			var obj = new Object;
			$.each(arr, function(k, v) {
				obj[v.name] = v.value;
			});
			return obj;
		}
	});
})(jQuery);