package cn.com.ssm.wechat.util;

import cn.com.ssm.wechat.constant.Constant;
import com.alibaba.fastjson.JSONObject;
import com.ivymei.framework.util.HttpUtil;
import com.ivymei.framework.util.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathy on 2017/12/21.
 */
public class JsPayUtil {
    public static String getOpenid(String code){
        String openid="";
        if(StringUtil.isNullOrBlank(code)){
            String baseUrl="http://localhost:8080/index.jsp";
            openid=createOauthUrlForCode(baseUrl);
        }else {
            openid= createOauthUrlForOpenid(code);
        }
        return openid;
    }

    public static String createOauthUrlForCode(String url){
        Map<String,String> map=new HashMap<String, String>();
        map.put("appid",Constant.appid);
        map.put("redirect_uri",url);
        map.put("response_type","code");
        map.put("scope","snsapi_base");
        map.put("state","STATE#wechat_redirect");
        String data=HttpUtil.httpPost(Constant.oauth2_url,map);
        JSONObject object=JSONObject.parseObject(data);
        String openid=object.getString("openid");
        return openid;
    }


    public static String createOauthUrlForOpenid(String code){
//        String oauth2_url = Constant.oauth2_url.replace("APPID", Constant.appid).replace("SECRET", Constant.appsecret).replace("CODE", code);
        Map<String,String > map=new HashMap<String, String>();
        map.put("appid",Constant.appid);
        map.put("secret",Constant.appsecret);
        map.put("code","CODE");
        map.put("grant_type","authorization_code");
        String data=HttpUtil.httpPost(Constant.oauth2_url,map);
//        String data=HttpUtil.httpGet(oauth2_url);
        JSONObject object=JSONObject.parseObject(data);
        String openid=object.getString("openid");
        return openid;
    }

    /**
     * 拼接签名字符串
     * 返回已经拼接好的字符串
     */
    public static void toUrlParams(Map<String,Object> urlObj){
        StringBuffer buffer=new StringBuffer();
        for(String key:urlObj.keySet()){
            if(!key.equals("sign")){
                buffer.append(key+"="+urlObj.get(key)+"&");
            }
        }

    }

    /**
     * 支付签名
     * @param timestamp
     * @param noncestr
     * @param packages
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String paySign(String timestamp, String noncestr,String packages) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", Constant.appid);
        paras.put("timestamp", timestamp);
        paras.put("noncestr", noncestr);
        paras.put("package", packages);
        paras.put("appkey", Constant.KEY);
        // appid、timestamp、noncestr、package 以及 appkey。
        String string1 = SignatureUtil.createSign(paras, false);
        String paySign = DigestUtils.shaHex(string1);
        return paySign;
    }

    /**
     * 支付回调校验签名
     * @param timestamp
     * @param noncestr
     * @param openid
     * @param issubscribe
     * @param appsignature
     * @return
     * @throws
     */
    public static boolean verifySign(long timestamp,
                                     String noncestr, String openid, int issubscribe, String appsignature) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid",Constant.appid);
        paras.put("appkey", Constant.KEY);
        paras.put("timestamp", String.valueOf(timestamp));
        paras.put("noncestr", noncestr);
        paras.put("openid", openid);
        paras.put("issubscribe", String.valueOf(issubscribe));
        // appid、appkey、productid、timestamp、noncestr、openid、issubscribe
        String string1 = SignatureUtil.createSign(paras, false);
        String paySign = DigestUtils.shaHex(string1);
        return paySign.equalsIgnoreCase(appsignature);
    }

}
