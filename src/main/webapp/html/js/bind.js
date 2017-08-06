var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount = 0;//当前剩余秒数

var errJson = JSON.parse(window.localStorage['errCodes']);
var confirm = false;
var mobilePhone = false;
/*
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}
var token= getCookie("jhxtoken");
$.ajax({
    url: '/mobile/customer',
    data: {accessToken:token},
    type: 'get',
    dataType: 'json',
    success: function (result) {
        var data = result.data;
        if(data && data.mobilePhone) {
            mobilePhone = data.mobilePhone;
            $("#mobilephone").val(mobilePhone);
           // $(".row_auth_code").hide();
        }

    },
    error: function (request, error) {

    }
});*/
var fadeIn =500;
var fadeOut = 2000;

//显示错误信息
var errInfo = function (errDiv, errCode, errText) {
    var errDivObj = $("#" + errDiv);
    var errMsg = errText || (errJson['' + errCode + ''] == undefined ? (errCode + ":" + errJson['999999']) : errJson['' + errCode + '']);
    errDivObj.text(errMsg).fadeIn(fadeIn);
    errDivObj.fadeOut(fadeOut);
    //setTimeout(function () {
    //    errDivObj.text('');
    //}, 3000);
}

var clearErr = function () {
    $('.err_tip').text(function () {
        return "";
    });
}

$(document).ready(function(){
    addPageEvent();
    $(".row_agreement").each(function () {
        $(this).click(function () {
            if ($("#rule_ico_id1").is(":hidden")) {
                $("#rule_ico_id1").show();
                $("#rule_ico_id2").hide();
                confirm = false;
            } else {
                $("#rule_ico_id1").hide();
                $("#rule_ico_id2").show();
                confirm = true;
            }
            canContinue();
        })
    });
//input propertychange
    $('#mobilephone').bind("focus",function(){
        deleteImg($(this));
    }).keyup(function () {
        deleteImg($(this));
        canContinue();
        canGetAuthCode();
        if(mobilePhone && $(this).val().trim().length==11 && $(this).val().trim() ==mobilePhone){
            $("#ok").addClass("button_able");
        }else {
            $("#ok").removeClass("button_able");
        }

    });//input propertychange
    $('#auth_code').bind('keyup', function () {
        deleteImg($(this));
        canContinue();
    });
    $('#confirmRules').bind('click', function () {
        canContinue();
    });//input propertychange
    $('#pic_auth_code').bind('keyup', function () {
        canGetAuthCode();
    });
});

/*$("#turnBack").click(function(){
    $.mobile.changePage($('#register_page'), {transition: "slide", reverse: false, changeHash: true});
    $("#register_page").css("display","block");
});*/

function addPageEvent() {
    $('#get_auth_code').addClass("auth_code_disable");
  //  $('#setpass').css('pointer-events', 'none');
   // $('#setpass').addClass("button_disable");
    $('#ok').addClass("button_disable");
    //获取授权码
    $('#get_auth_code').on('click', function () {
        clearErr();
        var that = $(this);
       // var uuid = $('#pic_auth_code').attr('data');
        var catpcha = $('#pic_auth_code').val().trim();
        var mobilephone = $('#mobilephone').val().trim();

        if(mobilephone==""){
            errInfo("register_err_tip", null,"请输入手机号");
            return ;
        }

        if(catpcha.length==0){
            errInfo("register_err_tip", null,"请输入图片验证码");
            return ;
        }
        that.addClass('button_clicked').css('pointer-events', 'none');
        curCount = count;
        var mobilephone = $('#mobilephone').val().trim();
        $.ajax({
            url: '../mobile/check_code.shtml',
            data: {mobilePhone: mobilephone, catpcha: catpcha,isRegisterValidate:1},
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log("register getAuth resp：" + JSON.stringify(data));
                that.removeClass('button_clicked').css('pointer-events', 'auto');
                if (data.resultCode == 100000) {
                    //设置button效果，开始计时
                    $('#get_auth_code').removeClass("button_able").addClass('button_disable').css('pointer-events', 'none').text(curCount);
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                } else {
                    curCount = 0;
                    errInfo("register_err_tip", data.errorCode);
                    $("#get_auth_code").text("获取验证码");
                    canGetAuthCode();
                }
            },
            error: function (request, error) {
                console.log('Get auth_code error!' + mobilephone);
                that.removeClass('button_clicked').css('pointer-events', 'auto');
            }
        });
    });
    //修改密码
  /*  $("#setpass").on('click', function () {
        $.mobile.changePage($('#finish_page'), {transition: "slide", reverse: false, changeHash: true});
        var catpcha = $('#auth_code').val().trim();
        var mobilephone = $('#mobilephone').val().trim();
        if(mobilePhone && mobilephone == mobilePhone ) {
            window.location.href="download.html";
        }

        if(mobilephone==""){
            errInfo("register_err_tip", null,"请输入手机号");
            return ;
        }
        if( $('#pic_auth_code').val().trim()==""){
            errInfo("register_err_tip", null,"请输入图形验证码");
            return ;
        }
        if(catpcha==""){
            errInfo("register_err_tip",null, "请输入手机验证码");
            return ;
        }

        if(confirm==false){
          //  errInfo("register_err_tip",null, "请同意是否同意");
            return ;
        }


        var mobilephone = $('#mobilephone').val().trim();
        $.ajax({
            url: '/mobile/jhx/check_auth_code',
            data: {mobilePhone: mobilephone, authCode:$('#auth_code').val().trim()},
            type: 'post',
            dataType: 'json',
            success: function (result) {
                var data = result.data;

                if (data.resultCode == 100000) {
                    window.location.href="jhxpass.html?mobilePhone="+$('#mobilephone').val().trim()
                        +"&authCode="+$('#auth_code').val().trim() ;
                } else {
                    errInfo("register_err_tip", data.errorCode);
                }
            },
            error: function (request, error) {
                console.log('Get auth_code error!');
            }
        });
    });
*/
    //点击注册
    $("#ok").on('click', function () {
        //var uuid = $('#pic_auth_code').attr('data');
    	var uuid = $('#pic_auth_code').val();
        var catpcha = $('#auth_code').val().trim();
        var mobilephone = $('#mobilephone').val().trim();
       /* if(mobilePhone && mobilephone == mobilePhone ) {
            window.location.href="download.html";
        }*/

        if(mobilephone==""){
            errInfo("register_err_tip", null,"请输入手机号");
            return ;
        }
        if( $('#pic_auth_code').val().trim()==""){
            errInfo("register_err_tip", null,"请输入图形验证码");
            return ;
        }
        if(catpcha==""){
            errInfo("register_err_tip",null, "请输入手机验证码");
            return ;
        }

        if(confirm==false){
            errInfo("register_err_tip",null, "请同意使用条款");
            return ;
        }

        $.ajax({
            url: '../mobile/modify_customer_info_phone.shtml',
            data: {mobilePhone: mobilephone,  authCode: catpcha},
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log("register getAuth resp：" + JSON.stringify(data));
                if (data.resultCode == 0) {
                    errInfo("register_err_tip", null,"已完成");
                    setTimeout(function(){
                        window.location.href="download.html";
                    },fadeIn+fadeOut);

                }else {
                    errInfo("register_err_tip", data.errorCode);
                }
            },
            error: function (request, error) {
                console.log('Get auth_code error!' + mobilephone);
            }
        });


      /*  var mobilePhone = $('#mobilephone').val().trim();
        var authCode = $('#auth_code').val().trim();
        var that = $(this);
        that.addClass('button_clicked');
        $.ajax({
            url: '/mobile/check/check_code',
            data: {mobilePhone: mobilePhone, authCode: authCode},
            type: 'post',
            dataType: 'json',
            success: function (result) {
                console.log("check checkCode resp：" + JSON.stringify(result));
                var data = result.data;
                that.removeClass('button_clicked');
                if (data.resultCode == 100000) {
                    window.location.href="download.html";
                   /!* $.mobile.changePage($('#finish_page'), {transition: "slide", reverse: false, changeHash: true});*!/
                } else {
                    errInfo("register_err_tip", data.errorCode, '验证码错误');
                }
            },
            error: function (error) {
                console.log('Check auth_code error!' + mobilePhone);
                that.removeClass('button_clicked');
            }
        });*/
    });
}

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#get_auth_code").removeClass('button_disable').addClass("button_able").text("获取验证码").css('pointer-events', 'auto');
        canGetAuthCode();
    }
    else {
        curCount--;
        $("#get_auth_code").text(curCount + '秒');
    }
}

function canGetAuthCode() {
    var hasMobilephone = isMobile($('#mobilephone').val().trim());
    var hasPicAuth = $('#pic_auth_code').val().trim();
    if (hasMobilephone && curCount == 0 && hasPicAuth.length > 0) {
        $('#get_auth_code')
            .removeClass('button_disable').addClass("button_able");
            ;
    } else {
        $('#get_auth_code')
            .addClass('button_disable').removeClass("button_able");
    }
}

function canContinue() {
    var mobilePhone = $('#mobilephone').val().trim();
    var authCode = $('#auth_code').val().trim();
    var hasMobilephone = isMobile(mobilePhone);
    var hasAuthcode = authCode.length >= 6;
    var ifConfirmRules = confirm;

    if (hasMobilephone && authCode.length > 0 && hasAuthcode && ifConfirmRules) {
        //$("#setpass").removeClass('button_disable')
         //   .addClass("set_pass_able").addClass("button_able");
        $("#ok").addClass("button_able").removeClass('button_disable');
    } else {
       // $("#setpass").addClass('button_disable').removeClass("button_able")
       //    .removeClass("set_pass_able");
        $("#ok").addClass("button_disable").removeClass('button_able');
    }
}

$(document).on('pageinit', '#register_page', function () {
    addSubmitEvent();
    $('#set_password').bind('input propertychange', function () {
        canSubmit();
    });
});

function addSubmitEvent() {
    $('#finish').css('pointer-events', 'none');
    $("#finish").on('click', function () {
        var that = $(this);
        that.addClass('button_clicked');
        var mobilephone = $('#mobilephone').val().trim();
        var auth_code = $('#auth_code').val().trim();
        var set_password = $('#set_password').val().trim();
        var confirm_password = $('#confirm_password').val().trim();
        if (set_password != confirm_password) {
            errInfo("finish_err_tip", null, '两次输入的密码不一致！');
            return;
        }
        $.ajax({
            url: '/mobile/register',
            data: {mobilePhone: mobilephone, authCode: auth_code, passWord: set_password},
            type: 'post',
            dataType: 'json',
            success: function (result) {
                var data = result.data;
                console.log("register submit resp：" + JSON.stringify(result));
                that.removeClass('button_clicked');
                if (data.resultCode == 100000) {
                    window.location.href = data.redirectUrl;
                } else if (data.errorCode == 100003) {
                    errInfo("finish_err_tip", data.errorCode);
                    window.location.href = '/mobile/index.html?username=' + mobilephone;
                } else {
                    errInfo("finish_err_tip", data.errorCode);
                }
            },
            error: function (request, error) {
                console.log('Register error!' + mobilephone);
                that.removeClass('button_clicked');
            }
        });
    });
}

function canSubmit() {
    var set_password = $('#set_password').val().trim();
    var hasPassword = isPasswd(set_password);
    var hasMobilephone = isMobile($('#mobilephone').val().trim());
    var hasAuthcode = $('#auth_code').val().trim().length >= 6;
    var ifConfirmRules = confirm;
    if (hasPassword && hasMobilephone && hasAuthcode && ifConfirmRules) {
        $("#ok").removeClass('button_disable').addClass("button_able");
            ;
    } else {
        $("#ok").addClass('button_disable').removeClass("button_able")
           ;
    }
}

//删除图片显示隐藏切换
function deleteImg(that) {
    if (that.val() != "") {
        that.parent().find(".delete_ico").css('opacity', '1')
            .css('pointer-events', 'auto');
    } else {
        that.parent().find(".delete_ico").css('opacity', '0')
            .css('pointer-events', 'none');
    }
}

/*删除图标删除输入框内容*/
$(".delete_ico").each(function () {
    $(this).click(function () {
        $(this).parent().find('input').val("").focus();
        $(this).css('opacity', '0')
            .css('pointer-events', 'none');
        canGetAuthCode();
        canContinue();
    })
});

/*显示密码*/
$(".eye_ico").each(function () {
    $(this).click(function () {
        var inputElement = $(this).parent().find('input');
        if (inputElement.attr('type') == 'text') {
            $(this).css('opacity', '0.5');
            inputElement.attr('type', 'password');
        } else {
            inputElement.attr('type', 'text');
            $(this).css('opacity', '1');
        }
    })
});

$("input").each(function () {
    $(this).focus(function () {
        $(this).css('color', '#3c3c3c');
        clearErr();
    });
    $(this).blur(function () {
        $(this).css('color', '#ababab');
    });
});



/**
 * 验证码
 */
/*function load_pic_auth() {
    $.ajax({
        url: '../tree/validateCode.do',
        type: 'post',
        success: function (data) {
            $('#pic_auth_code').attr('data', data.code);
        }
    });
}
load_pic_auth();

$('#get_pic_auth_code').click(function () {
    load_pic_auth();
});*/


