package cn.com.ssm.common.model.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 顾客年龄消费分析明细
 * @author: Jarvix
 * @date: 2017/7/20
 */
@Document(collection = "report_token")
public class MBReportToken extends BaseMongDbEntity {
    private String mobile;
    private Integer memberId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

