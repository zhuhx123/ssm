package cn.com.ssm.wechat.controller;

import cn.com.ssm.wechat.common.JsPayResult;
import cn.com.ssm.wechat.common.ResultObject;
import cn.com.ssm.wechat.common.UnifiedOrderParams;
import cn.com.ssm.wechat.constant.Constant;
import cn.com.ssm.wechat.service.NotifyService;
import cn.com.ssm.wechat.service.UnifiedOrderService;
import cn.com.ssm.wechat.util.SignatureUtil;
import cn.com.ssm.wechat.util.WxPay;
import cn.com.ssm.wechat.util.XmlUtil;
import cn.com.ssm.wechat.util.XmltoJsonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Nathy on 2017/12/22.
 */
@Controller
@RequestMapping("/pay")
public class WxPayController {
    @Autowired
    UnifiedOrderService unifiedOrderService;

    @RequestMapping("/wxpay")
    public String pay(HttpServletRequest request, Model m) {
        StringBuffer url = request.getRequestURL();
        url = new StringBuffer(url.substring(0, url.lastIndexOf(request.getRequestURI())));
        url.append(request.getContextPath() + "/pay/notify");
        String notityUrl = url.toString();
        String code = request.getParameter("code");
        String orderSn = request.getParameter("orderSn");
        UnifiedOrderParams params = WxPay.getUnifiedOrderParams(orderSn, code);
        params.setNotify_url(notityUrl);
        String data = unifiedOrderService.unifiedOrder(params, Constant.KEY);
        JsPayResult result = JSONObject.parseObject(data, JsPayResult.class);
        String prepay_id = result.getData().get(0);

        String timeStamp = SignatureUtil.getTimeStamp();
        String nonceStr = SignatureUtil.getNonceStr();
        SortedMap<Object, Object> signParams = new TreeMap<Object, Object>();
        signParams.put("appId", Constant.appid);
        signParams.put("timeStamp", timeStamp);
        signParams.put("nonceStr", nonceStr);
        signParams.put("signType", "MD5");
        signParams.put("package", "prepay_id=" + prepay_id);
        // 生成支付签名，要采用URLENCODER的原始值进行SHA1算法！
        String sign = SignatureUtil.createSign(signParams, Constant.KEY);
        signParams.put("pg", prepay_id);
        m.addAttribute("paySign", sign);

        m.addAttribute("packageValue", "prepay_id=" + prepay_id);
//        WebUtil.response(response, WebUtil.packJsonp(callback,
//                JsonUtil.warpJsonNodeResponse(JsonUtil.objectToJsonNode(payMap)).toString()));
        return "跳转到你的支付页面";
    }

    @RequestMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // String xml =
        // "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]>
        // </bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type>
        // <is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id>
        // <nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid>
        // <out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code>
        // <return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign>
        // <time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type>
        // <transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
        ResultObject result = new ResultObject();// 返回数据结果集合
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            String content = new String(out.toByteArray(), "utf-8");//xml数据
            JSONObject jsonObject = JSONObject.parseObject(XmltoJsonUtil.xml2JSON(content));
            JSONObject result_xml = jsonObject.getJSONObject("xml");
            JSONArray result_code = result_xml.getJSONArray("result_code");
            String code = (String) result_code.get(0);
            if (code.equalsIgnoreCase("FAIL")) {
                result.setMsg("微信统一订单下单失败");
                result.setResultCode("-1");

                response.getWriter().write(XmlUtil.setXML("SUCCESS", "OK"));

            } else if (code.equalsIgnoreCase("SUCCESS")) {
                result.setMsg("微信统一订单下单成功");
                result.setResultCode("1");
                JSONArray out_trade_no = result_xml.getJSONArray("out_trade_no");//订单编号
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("orderNum", (String) out_trade_no.get(0));
                map.put("consumState", 1);
                // accountWalletService.updateAccountOrderState(map);
                response.getWriter().write(XmlUtil.setXML("SUCCESS", "OK"));

            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setResultCode("-1");
            return;
        }

//        String result = notifyService.notity(XmlUtil.);
//        response.getWriter().write(result);
//        response.getWriter().flush();
//        response.getWriter().close();
    }
}
