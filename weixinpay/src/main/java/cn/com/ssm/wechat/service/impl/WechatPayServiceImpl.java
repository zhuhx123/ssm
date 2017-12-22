package cn.com.ssm.wechat.service.impl;

import cn.com.ssm.wechat.constant.Constant;
import cn.com.ssm.wechat.service.WechatPayService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * res预支付订单 字符串
 * 微信支付 url
 * Created by Nathy on 2017/12/22.
 */
public class WechatPayServiceImpl implements WechatPayService {

    public void wechatPay(Model model, String res, String url) {
//        try {
//            Map<String, String> start = new HashMap<String,String>();
//            StringBuilder startSign = new StringBuilder();
//
//            Map<String, String> pay = new HashMap<String,String>();
//            StringBuilder paySign = new StringBuilder();
//            XmlMapper xmlMapper = new XmlMapper();
//            JsonNode node = xmlMapper.readTree(res);
//            if (StringUtils.equals(node.get("return_code").asText(), "SUCCESS")) {
//                // 得到的预支付订单，重新生成微信支付参数
//                String prepay_id = node.get("prepay_id").asText();
//              //  String jsapi_ticket = jsapiTicket();
//                // 生成 微信支付 config 参数
//                start.put("appId", Constant.appid);
//                start.put("nonceStr", encoder.createNonceStr());
//                start.put("timestamp", createTimeStamp());
//                // 生成 config 签名
//            //    startSign.append("jsapi_ticket=").append(jsapi_ticket);
//                startSign.append("&noncestr=").append(start.get("nonceStr"));
//                startSign.append("&timestamp=").append(start.get("timestamp"));
//                startSign.append("&url=").append(url);
//                start.put("signature", encoder.encode(startSign.toString()));
//
//                // config信息验证后会执行ready方法的参数
//                pay.put("signType", "MD5");
//                pay.put("packageStr", "prepay_id=" + prepay_id);
//                // 生成支付签名
//                paySign.append("appId=").append(start.get("appId"));
//                paySign.append("&nonceStr=").append(start.get("nonceStr"));
//                paySign.append("&package=").append(pay.get("packageStr"));
//                paySign.append("&signType=").append(pay.get("signType"));
//                paySign.append("&timeStamp=").append(start.get("timestamp"));
//                paySign.append("&key=").append(Constant.KEY);
//                pay.put("paySign", encoder.encode(paySign.toString()));
//                // 将微信支参数放入 model 对象中以便前端使用
//                model.addAttribute("start", start);
//                model.addAttribute("pay", pay);
//            }
//        } catch (Exception e) {
//            model.addAttribute("wechatMessage", "微信授权失败!");
//            e.printStackTrace();
//        }
    }

    public String createTimeStamp() {
                 return Long.toString(System.currentTimeMillis() / 1000);
             }

    /**
     * 获取微信 JSAPI 支付的临时票据
     *
     * @return 临时票据
     */
//    public String jsapiTicket() {
//        if (checkJsapiTicket()) {
//            // 声明 获取临时票据路径
//            StringBuilder ticketBuilder = new StringBuilder();
//            ticketBuilder.append(Constant.TICKET_API);
//            ticketBuilder.append("?access_token=").append(getToken());
//            ticketBuilder.append("&type=jsapi");
//            // 获取 临时票据
//            Map<?, ?> jsapiTicket = restTemplate.getForObject(ticketBuilder.toString(), Map.class);
//            Configure.setJsapiTicket((String) jsapiTicket.get("ticket"));
//        }
//        return Configure.getJsapiTicket();
//    }

    public static boolean checkJsapiTicket() {
        if (!StringUtils.isEmpty(Constant.jsapiTicket)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Constant.jsapiTicketTime);
            calendar.add(Calendar.SECOND, 7200);
            return calendar.before(new Date());
        }
        return true;
    }
}
