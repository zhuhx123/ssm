package cn.com.ssm.wechat.service;

import cn.com.ssm.wechat.common.UnifiedOrderParams;

/**统一下单
 * Created by Nathy on 2017/12/21.
 */
public interface UnifiedOrderService {
     String unifiedOrder(UnifiedOrderParams data,String key);
}
