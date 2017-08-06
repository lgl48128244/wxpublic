/**
 * Created by wangminglei on 2015-4-2.
 */
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
//手机号校验
function isMobile(mobile){
	//return !!mobile.match(/^(?=.{6,16}$)(?![0-9]+$)(?!.*(.).*\1)[0-9a-zA-Z]+$/);
    return !!mobile.match(/^1\d{10}$/);
}

//密码校验 只能是数字字母或特殊字符
function isPasswd(pwd)
{
//    return !!pwd.match(/^(\w){6,15}$/);
      return !!pwd.match(/^[\x00-\xff]{6,15}$/);
}

//邮箱校验
function isEmail(email){
    //return !!email.match(/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/);
    var eLength = email.length;
    return eLength>=5 && eLength<=40 && email.indexOf('@')>0;
}
