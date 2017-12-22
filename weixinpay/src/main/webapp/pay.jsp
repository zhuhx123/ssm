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
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/front/js/weixin.js"></script>
    <script type="text/javascript">
        $("#onlinePayNow").click(function getpay(){
            if (typeof WeixinJSBridge=="undefined") {
                if (document.addEventListener) {document.addEventListener('WeixinJSBridgeReady',onBridgeReady,false);
                }else if(document.attachEvent){document.attachEvent('WeixinJSBridgeReady',onBridgeReady);document.attachEvent('onWeixinJSBridgeReady',onBridgeReady);
                }
            }else{
                //如果报错，可用下面方法看看是不是参数缺少。
                /* alert('${appId}');
                 alert('${paytimestamp}');
                 alert('${paynonceStr}');
                 alert('${paypackage}');
                 alert('${paysignType}');
                 alert('${paySign}');  */
                //调用下面方法。开启微信支付。
                onBridgeReady();
            }
        })

        function onBridgeReady(){
            WeixinJSBridge.invoke('getBrandWCPayRequest', {
                "appId" : '${appId}', //公众号名称，由商户传入
                "timeStamp" : '${paytimestamp}', //时间戳，自1970年以来的秒数
                "nonceStr" : '${paynonceStr}', //随机串
                "package" : '${paypackage}',
                "signType" : '${paysignType}', //微信签名方式:
                "paySign" : '${paySign}' //微信签名
            }, function(res) {
                //alert(res.err_msg); // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返
                if(res.err_msg == "get_brand_wcpay_request:ok"){
                    //支付成功，完成后去到哪个页面。
                    window.location.href="/XXXX/xxxx.html";
                }
            });
        }
    </script>
</head>
<body>
<input type="hidden" name="appId" value="${appId}" id="appid" />
<div class="button" id="onlinePayNow">确认支付</div>
</body>
</html>