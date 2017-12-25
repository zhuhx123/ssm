package cn.com.ssm.wechat.service.impl;

import cn.com.ssm.wechat.common.JsPayResult;
import cn.com.ssm.wechat.common.UnifiedOrderParams;
import cn.com.ssm.wechat.constant.Constant;
import cn.com.ssm.wechat.service.UnifiedOrderService;
import cn.com.ssm.wechat.util.HttpReqUtil;
import cn.com.ssm.wechat.util.SignatureUtil;
import cn.com.ssm.wechat.util.XmlUtil;
import cn.com.ssm.wechat.util.XmltoJsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

/**统一下单
 * Created by Nathy on 2017/12/21.
 */
@Service
public class UnifiedOrderServiceImpl implements UnifiedOrderService {
    Logger logger=Logger.getLogger(UnifiedOrderServiceImpl.class);

    public String unifiedOrder(UnifiedOrderParams data,String key) {
        //统一下单支付
        String returnXml = null;
        JsPayResult result=new JsPayResult();
        try {
            //生成sign签名
            SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
            parameters.put("appid", data.getAppid());
            parameters.put("attach", data.getAttach());
            parameters.put("body", data.getBody());
            parameters.put("mch_id", data.getMch_id());
            parameters.put("nonce_str", data.getNonce_str());
            parameters.put("notify_url", data.getNotify_url());
            parameters.put("out_trade_no", data.getOut_trade_no());
            parameters.put("total_fee", data.getTotal_fee().toString());
            parameters.put("trade_type", data.getTrade_type());
            parameters.put("spbill_create_ip", data.getSpbill_create_ip());
            parameters.put("openid", data.getOpenid());
            parameters.put("device_info", data.getDevice_info());
            logger.info("SIGN:"+ SignatureUtil.createSign(parameters,key));
            data.setSign(SignatureUtil.createSign(parameters,key));  //获取签名
//            XStream xs = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_", "_")));
//            xs.alias("xml", WxPaySendData.class);
            parameters.put("sign",data.getSign());
            String xml = XmlUtil.getRequestXML(parameters);
            logger.info("统一下单xml为:\n" + xml);
            String content= HttpReqUtil.sendPost(Constant.createOrderURL,xml);
            logger.info("返回结果:" + content);
//            HttpClientUtil util = HttpClientUtil.getInstance();
//            returnXml = util.doPostForString("https://api.mch.weixin.qq.com/pay/unifiedorder", null, xml);
            logger.info("返回结果:" + returnXml);

            Map map=XmlUtil.doXMLParse(content);

            JSONObject jsonObject = JSONObject.parseObject(XmltoJsonUtil.xml2JSON(content)) ;
            JSONObject result_xml = jsonObject.getJSONObject("xml");
            JSONArray result_code =  result_xml.getJSONArray("result_code");
            String code = (String)result_code.get(0);

            List<String> data2 = new ArrayList<String>();
            if(code.equalsIgnoreCase("FAIL")){
                result.setErrMsg("微信统一订单下单失败");
                result.setResultCode("-1");
                result.setData(data2);
            }else if(code.equalsIgnoreCase("SUCCESS")){
                JSONArray prepay_id = result_xml.getJSONArray("prepay_id");
                String prepayId = (String)prepay_id.get(0);
                data2.add(prepayId);
                result.setErrMsg("微信统一订单下单成功");
                result.setResultCode("1");
                result.setData(data2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
}
