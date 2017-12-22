package cn.com.ssm.wechat.util;

import cn.com.ssm.wechat.common.UnifiedOrderParams;
import cn.com.ssm.wechat.constant.Constant;
import cn.com.ssm.wechat.service.UnifiedOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by Nathy on 2017/12/21.
 */
public class WxPay {

    public static UnifiedOrderParams getUnifiedOrderParams(String payOrderSn,String code){
        String out_trade_no=payOrderSn;
        BigDecimal total_fee=new BigDecimal(0.01);
        Integer status=1;
        String subject="I美平台套餐订单号"+payOrderSn;
        String body=subject;
        String notify_url="/pay/notify.php";
        String trade_type="JSAPI";
        String openid=JsPayUtil.getOpenid(code);
        String mch_id= Constant.mch_id;
        String spbill_create_ip="127.0.0.1";
        String nonce_str=SignatureUtil.getNonceStr();
        UnifiedOrderParams params=new UnifiedOrderParams();
        params.setAppid(Constant.appid);
        params.setAttach(body);
        params.setBody(body);
        params.setMch_id(mch_id);
        params.setNonce_str(nonce_str);
        params.setNotify_url(notify_url);
        params.setOut_trade_no(out_trade_no);
        params.setTotal_fee(total_fee);
        params.setTrade_type(trade_type);
        params.setSpbill_create_ip(spbill_create_ip);
        params.setOpenid(openid);
        return params;
    }


//        public JsPayResult jsPay(String openId){
//            JsPayResult result=null;
//            System.out.print("****正在支付的openId****" + openId);
//            // 统一下单
//            String out_trade_no="";
////            String out_trade_no = PayUtil.createOutTradeNo();
//            BigDecimal total_fee =new BigDecimal(0.01); // 产品价格1分钱,用于测试
////            String spbill_create_ip = HttpReqUtil.getRemortIP(request);
//            String spbill_create_ip="127.0.0.1";
//            System.out.print("支付IP" + spbill_create_ip);
////            logger.info("支付IP" + spbill_create_ip);
//            String nonce_str = PayUtil.createNonceStr(); // 随机数据
//            //参数组装
//            UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
//            unifiedOrderParams.setAppid("");// 必须
//            unifiedOrderParams.setMch_id("");// 必须
//            unifiedOrderParams.setOut_trade_no(out_trade_no);// 必须
//            unifiedOrderParams.setBody("支付测试");// 必须
//            unifiedOrderParams.setTotal_fee(total_fee); // 必须
//            unifiedOrderParams.setNonce_str(nonce_str); // 必须
//            unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
//            unifiedOrderParams.setTrade_type("JSAPI"); // 必须
//            unifiedOrderParams.setOpenid(openId);
//            unifiedOrderParams.setNotify_url("");// 异步通知url
//            // 统一下单 请求的Xml(正常的xml格式)
//            String unifiedXmL = wechatPayService.abstractPayToXml(unifiedOrderParams);////签名并入service
//            // 返回<![CDATA[SUCCESS]]>格式的XML
////            String content = HttpUtil.sendPost(UNIFORMORDER, xml);
////            String unifiedOrderResultXmL = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST_METHOD,WeChatConfig.UNIFIED_ORDER_URL, null, unifiedXmL);
//            // 进行签名校验
//            if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedOrderResultXmL)) {
//                String timeStamp = PayUtil.createTimeStamp();
//                //统一下单响应
//                UnifiedOrderResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedOrderResultXmL, UnifiedOrderResult.class);
//                /**** 用map方式进行签名 ****/
//                // SortedMap<Object, Object> signMap = new TreeMap<Object,
//                // Object>();
//                // signMap.put("appId", WeiXinConfig.APP_ID); // 必须
//                // signMap.put("timeStamp", timeStamp);
//                // signMap.put("nonceStr", nonceStr);
//                // signMap.put("signType", "MD5");
//                // signMap.put("package", "prepay_id=" + prepay_id);
//                // String paySign = SignatureUtil.createSign(signMap, key, SystemConfig.CHARACTER_ENCODING);
//                result = new JsPayResult();
//                result.setAppId(WeChatConfig.APP_ID);
//                result.setTimeStamp(timeStamp);
//                result.setNonceStr(unifiedOrderResult.getNonce_str());//直接用返回的
//                /**** prepay_id 2小时内都有效，再次支付方法自己重写 ****/
//                result.setPackageStr("prepay_id=" + unifiedOrderResult.getPrepay_id());
//                /**** 用对象进行签名 ****/
//                String paySign = SignatureUtil.createSign(result, WeChatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
//                result.setPaySign(paySign);
//                result.setResultCode("SUCCESS");
//            } else {
//                logger.info("签名验证错误");
//            }
//            /**** 返回对象给页面 ****/
//            return result;
//        }
}
