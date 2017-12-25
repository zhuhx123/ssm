<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>微信支付</title>
    <meta name="keywords" content="关键字,关键字">
    <meta name="description" content="">
    <script type="text/javascript" src="${basePath}/js/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="${basePath}/js/wechat/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="${basePath}/js/imei/utils.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#pay").bind("click", function () {
                $.ajax({
                    url: "${basePath}/pay/wxpay",
                    type: "post",
                    async: true,
                    data: $("#payForm").serialize(),
                    dataType: 'json',
                    success: function (data) {
                        var code = data.imei_error_code;
                        var message = data.imei_error_msg;
                        if (code != '0') {
                            alert(message);
                            return;
                        }
                        var jspay = data; // 接收后台传递过来的支付串
                        // 调用微信支付JS接口
                        WeixinJSBridge.invoke('getBrandWCPayRequest', jspay, function (res) {
                            if (res.err_msg == 'get_brand_wcpay_request:ok') {
                                // 支付已完成！,注意这里支付完成指的是前端JS发起支付的操作顺利完成，
                                //并不意味着真正的支付成功，强烈建议到支付回调中去校验支付成功与否。
                                alert("支付成功！");
                                var redirectUrl = data.redirectUrl;
                                if (redirectUrl) {
                                    window.location = redirectUrl;
                                } else {
                                    window.location = "";//默认的支付成功页面
                                }
                            } else if (res.err_msg == 'get_brand_wcpay_request:fail') {
                                // 支付出现问题，请稍后再试！
                                alert("支付失败！");
                            } else if (res.err_msg == 'get_brand_wcpay_request:cancel') {
                                // 已取消支付，请重新支付！
                                alert("支付取消！");
                            } else {
                                // 支付被拒绝了
                            }
                        });
                    },
                    error: function (edata) {
                        alert("服务器异常")
                    }
                });
            });
        });

    </script>
</head>
<body>
<form id="payForm">
    <input type="text" name="price"/>
    <input type="hidden" name="appId" value="${appId}"/>
    <input type="hidden" name="orderSn" value="111"/>
    <input id="pay" type="button" value="支付"/>
</form>
</body>
</html>