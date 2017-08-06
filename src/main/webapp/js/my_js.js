function controlButton(moduleId){
	 $.ajax({
			url:'${pageContext.request.contextPath}/controller/manager_getPermissButton.action?module.Id='+moduleId,
       		dataType:'text',
       		//async:false,
       		type:'post',
			success:function(data){
				result = $.parseJSON(data);
				if("1"!=result[0].madd){
					var currentBtn = document.getElementById('btnAdd');
			        currentBtn.style.display = "none";
				}
				if("1"!=result[0].mdel){
					var currentBtn = document.getElementById('btnDel');
			        currentBtn.style.display = "none";
				}
				if("1"!=result[0].medit){
					var currentBtn = document.getElementById('btnUpdate');
			        currentBtn.style.display = "none";
				}
				
			}
		 });
}


function formToObject(form) {//
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
}

function showMsg(title, msg) {
	$.messager.show({
		title : title,
		msg : msg,
		timeout : 4000,
		showType : 'slide'
	});
}
// 鏄剧ずwindow绐楀彛
function showWin(msg) {
	$.messager.alert("错误", msg, 'error');
}

// 吧毫秒转换成日期
function MillisecondToDate(msd) {
	var newTime = new Date(msd);
	var res = "";
	res += (newTime.getHours()) + "：";
	res += (newTime.getMinutes() + 1);
	return res;
}

//移动图片
function sendimg(id,imgname,type){
	 $.ajax({
		url:'fileCopye.action',
		async:false,
		type:'post',
		dataType:'text',
		data:'id='+id+'&filename='+imgname+'&type='+type,
		success:function(data){
			if(!data){
				alert("图片上传异常");
			}
		}
	 });
}

$.fn.serializeJson=function(){  
    var serializeObj={}; // 目标对象
    var array=this.serializeArray(); // 将form数据转换为数组
    $(array).each(function(){ // 遍历数组 
        if(serializeObj[this.name]){  // 取name属性对应value ，if成立，在对象中已经存在该key
            if($.isArray(serializeObj[this.name])){ // value本身已经是数组格式 
                serializeObj[this.name].push(this.value); // 将新value 加入原来数组
            }else{  
                serializeObj[this.name]=[serializeObj[this.name],this.value];  // 将多个值变为数组
            }  
        }else{  
            serializeObj[this.name]=this.value;  // 目标属性不存在，添加新属性
        }  
    });  
    return serializeObj;  
};

function saveCookie(uid,uname){
	if(uid==undefined||uid===null||uid==""){
		return ;
	}
	var uinfo = $.cookie("userinfo");
	if(uinfo===null||uinfo=="null"||uinfo==undefined)uinfo="";
	
	var cuarr = uinfo.split("|");
	
	var cstr = uid+","+uname;
	
	var rs = true;
	for(var i = 0;i<cuarr.length;i++){
		var tmpopt = cuarr[i].toString();
		var tmparr = tmpopt.split(",");
		if(tmparr[0]!=""){
			if(tmparr[0]==uid){
				rs = false;
				break;
			}
		}
	}
	if(rs){
		if(cuarr.length>10){
			cuarr.shift();//移除第一个
		}
		cuarr.push(cstr);
	}
	cstr = cuarr.join("|");
	
	$.cookie("userinfo", cstr,{ path: '/' });
	//alert($.cookie("userinfo"));
}

function getCookie(inputid){
	$("#cookieSelwin").remove();
	
	var html = "<div id='cookieSelwin'>" +
			"<table style='font-size:13px;'>";
	
	var cookies = $.cookie("userinfo");
	
	var arr = cookies.split("|");
	
	html+="<tr>";
	html+="<td width='100'>用户ID</td>";
	html+="<td>用户名称</td>";
	html+="</tr>";
	
	for ( var i = 0; i < arr.length; i++) {
		if(arr[i]===null||arr[i].split(",").length!=2)continue;
		html+="<tr>";
		html+="<td>" +
				"<a href='javascript:;' onclick=\"$('#"+inputid+"').val("+arr[i].split(",")[0]+")\">"+arr[i].split(",")[0]+"</a>" +
				"</td>";//ID
		
		html+="<td>" +
				"<span>"+arr[i].split(",")[1]+"</span>" +
				"</td>";//Name
		html+="</tr>";
	}
	
	html+="</table></div>";
	$("body").append(html);
	//$.parser.parse();
	$('#cookieSelwin').window({
	    width:300,
	    height:370,
	    title:'本地检索',
	    modal:true
	});
	$('#cookieSelwin a').click(function (){
		$('#cookieSelwin').window("close");
	});
}