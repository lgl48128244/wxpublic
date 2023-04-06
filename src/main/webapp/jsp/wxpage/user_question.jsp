<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>反馈问题</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport">
    <link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/open/libs/weui/0.4.3/weui.css"/>
    <style type="text/css">
        .picture {
            float: left;
            margin-right: 10px;
            margin-bottom: 10px;
            /* margin-top: 1px; */
            width: 70px;
            height: 90px;
            box-shadow: 1px 3px 8px rgba(0, 0, 0, 0.2);
            padding: 2px;
            cursor: pointer;
        }
    </style>
    <script type='text/javascript' src="js/jquery.min.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript">
        var images = {
            localId: [],
            serverId: []
        };
        var openid = '${follower.openid}';
        var arr = new Array();
        $(function () {
            $.ajax({
                url: "main/jssdk.do",
                type: 'post',
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: {
                    'url': location.href.split('#')[0]
                },
                success: function (data) {
                    // 微信信息的以及调用的配置
                    wx.config({
                        debug: false,
                        appId: data.appId,
                        timestamp: data.timestamp,
                        nonceStr: data.nonceStr,
                        signature: data.signature,
                        jsApiList: ['checkJsApi', 'onMenuShareTimeline',
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
                            'openCard']
                    });
                }
            });

            /**
             * 多图片上传
             */
            $('#uploadImg').on('click', function () {
                wx.chooseImage({
                    success: function (res) {
                        var localIds = res.localIds;
                        if (res.localIds.length > 5) {
                            alert("最多5张");
                            return;
                        } else {
                            syncUpload(localIds);
                        }
                    }
                });
            });
            var syncUpload = function (localIds) {
                var localId = localIds.pop();
                if (arr.length > 4) {
                    alert("最多5张");
                    return;
                }
                wx.uploadImage({
                    localId: localId,
                    success: function (res) {
                        var serverId = res.serverId; // 返回图片的服务器端ID
                        $.ajax({
                            type: 'post',
                            url: 'upload.do',
                            data: {'mediaId': serverId},
                            dataType: 'json',
                            success: function (data) {
                                arr.push(data.path);
                                $("#picBox").append("<img src='" + data.url + "' class='picture'>");
                            },
                            error: function (data) {
                                alert("上传失败");
                            }
                        });
                        //其他对serverId做处理的代码
                        if (localIds.length > 0) {
                            syncUpload(localIds);
                        }
                    }
                });
            };

            $("#btn").click(function () {
                $("#wxUserId").val(openid);
                $("#wxUserPicname").val(arr);
                $.ajax({
                    type: "POST",
                    url: "notice/saveNotice.do",
                    data: $("form").serialize(),
                    success: function (data) {
                        //清空表单
                        document.getElementById("addForm").reset();
                        //删除原有图片
                        document.getElementById("picBox").parentNode.removeChild(document.getElementById("picBox"));
                        alert(data.message);
                    },
                    error: function (data) {
                        alert(data.message);
                    }
                });
            });
        });
    </script>
</head>
<body>
<form action="notice/saveNotice.do" method="post" id="addForm">
    <input type="hidden" id="wxUserId" name="wxUserId">
    <input type="hidden" id="wxUserPicname" name="wxUserPicname">
    <input type="hidden" value="0" name="status">
    <table>
        <tr>
            <td>问题类型：</td>
            <td>
                <select name="wxQuestionType">
                    <option value="物流">物流</option>
                    <option value="书籍">书籍</option>
                    <option value="观看">观看</option>
                    <option value="购买">购买</option>
                    <option value="其它">其它</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>手机号码：</td>
            <td>
                <input type="text" name="wxUserPhone" size="20">
            </td>
        </tr>
        <tr>
            <td>上传图片</td>
            <td>
                <input type="button" id="uploadImg" value="照片上传"/>
                <div id="picBox"></div>
            </td>
        </tr>
        <tr>
            <td>问题描述：</td>
            <td>
                <textarea rows="5" cols="20" name="wxUserQuestion" placeholder="请在这里输入您的意见！"></textarea>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <input type="button" id="btn" value="提交">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>  