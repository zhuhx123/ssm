package cn.com.ssm.wechat.constant;

import java.util.Date;

/**
 * Created by Nathy on 2017/12/21.
 */
public class Constant {
    // 第三方用户唯一凭证
    public static String appid = "wx46f8461f164cfaf5";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "10cbf01d2ac48eb528c6c2ec6bead5eb";
    //商户ID
    public static String mch_id="1467456302";
    //微信商户平台-账户设置-安全设置-api安全,配置32位key
    public static String KEY  = "rCiGgbDm14eF6UGcQjmxeCkaefhg5ree";
    //获取openId
//    public static String oauth2_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static String oauth2_url = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public static final String createOrderURL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String backUri="http://XXXXXXXX/api/weixin/topay.jhtml";//微信支付下单地址
    public static final String notify_url="http://XXXXXXXXXX/api/weixin/notify.jhtml";//异步通知地址
    // 临时票据API
    public static String TICKET_API = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    public static String jsapiTicket = null;
    public static Date jsapiTicketTime = null;
}
