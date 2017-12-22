package cn.com.ssm.wechat.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathy on 2017/12/21.
 */
public class JsPayResult extends JsPayParams {
    private static final long serialVersionUID = 392188712101246402L;

    private String errMsg;

    private String resultCode;

    private List<String> data=new ArrayList<String>();

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
