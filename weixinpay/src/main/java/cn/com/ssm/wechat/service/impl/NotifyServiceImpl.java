package cn.com.ssm.wechat.service.impl;

import cn.com.ssm.wechat.service.NotifyService;

/**
 * Created by Nathy on 2017/12/22.
 */
public class NotifyServiceImpl implements NotifyService {

    public String notity(String data) {
//        System.out.println("==开始进入h5支付回调方法==");
//       String xml_review_result = WeiXinUtil.getXmlRequest(request);
//        System.out.println("微信支付结果:"+xml_review_result);
//        Map<String, String> resultMap = null;
//        try {
//            resultMap = XmlUtil.doXMLParse(xml_review_result);
//            System.out.println("resultMap:"+resultMap);
//            if(resultMap != null && resultMap.size() > 0){
//                String sign_receive = (String)resultMap.get("sign");//返回的签名
//                System.out.println("sign_receive:"+sign_receive);
//                resultMap.remove("sign");//删除签名
//                String checkSign= SignatureUtil.createSign(resultMap, Constant.KEY);
////                String checkSign = SignatureUtil.getSign(resultMap,Global.getResource("weixin.key"));//生成新签名
//                System.out.println("checkSign:"+checkSign);
//
//                //签名校验成功
//                if(checkSign != null && sign_receive != null &&
//                        checkSign.equals(sign_receive.trim())){
//                    System.out.println("weixin receive check Sign sucess");
//                    try{
//                        //获得返回结果
//                        String return_code = (String)resultMap.get("return_code");
//
//                        if("SUCCESS".equals(return_code)){//交易成功
//                            String out_trade_no = (String)resultMap.get("out_trade_no");//获取商户的订单号
//                            System.out.println("weixin pay sucess,out_trade_no:"+out_trade_no);
//                            //处理支付成功以后的逻辑，处理订单,相关的商品 减去相应数量
//
//                            String resultXml="<xml><return_code><![CDATA[SUCCESS]]></return_code>"+
//                                    "<return_msg><![CDATA[OK]]></return_msg></xml>";
//                            return resultXml;
//
//                        }else{//交易失败
//                            String resultXml="<xml><return_code><![CDATA[FAIL]]></return_code>"+
//                                    "<return_msg><![CDATA[FAIL]]></return_msg></xml>";
//                            return resultXml;
//                        }
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
//                }else{
//                    //签名校验失败
//                    System.out.println("weixin receive check Sign fail");
//                    String checkXml = "<xml><return_code><![CDATA[FAIL]]></return_code>"+
//                            "<return_msg><![CDATA[check sign fail]]></return_msg></xml>";
//                    return checkXml;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
