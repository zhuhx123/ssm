/**
 * Created by 20170331 on 2017/12/7.
 */
function isWxPay(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }
    return false;
}

function isAliPay(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/AlipayClient/i) =='alipayclient'){
        return true;
    }
    return false;
}