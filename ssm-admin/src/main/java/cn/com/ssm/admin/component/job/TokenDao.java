package cn.com.ssm.admin.component.job;

import cn.com.ssm.common.model.mongo.MBReportToken;

import java.util.List;

/**
 * Created by Nathy on 2017/12/12.
 */
public interface TokenDao {
    List<MBReportToken> listToken();

    void insertToken(MBReportToken token);

    MBReportToken selectById(String id);
}
