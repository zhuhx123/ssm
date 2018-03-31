package cn.com.ssm.admin.component.quartz.job;

import com.ivymei.framework.util.DateUtil;

/**
 * Created by Nathy on 2018/2/8.
 */
public class TestJob {
    public void excute() {
        System.out.print(DateUtil.getCurrDateStr(DateUtil.DEFAULT_DATE_TIME_FORMAT) + "=========执行了一次");
    }
}
