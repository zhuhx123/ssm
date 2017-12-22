package cn.com.ssm.wechat.util;

import com.ivymei.framework.util.Md5Encrypt;

import java.util.*;

/**
 * Created by Nathy on 2017/12/21.
 */
public class SignatureUtil {
    //创建md5 数字签证
    public static String createSign(SortedMap<Object,Object> parameters,String key){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = Md5Encrypt.md5ByUTF8(sb.toString()).toUpperCase();
        return sign;
    }

//    public static String getNonceStr() {
//        Random random = new Random();
//        return Md5Encrypt.md5(String.valueOf(random.nextInt(10000)), "UTF-8");
//    }

    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    // 随机字符串生成
    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取32位随机字符串
     * @return
     */
    public static String getNonceStr(){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        for(int i = 0 ; i < 32 ; i ++ ){
            sb.append(str.charAt(rd.nextInt(str.length())));
        }
        return sb.toString();
    }
}
