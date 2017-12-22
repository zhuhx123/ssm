package cn.com.ssm.wechat.service;

import org.springframework.ui.Model;

/**
 * Created by Nathy on 2017/12/22.
 */
public interface WechatPayService {
    void wechatPay(Model model, String res, String url);
}
