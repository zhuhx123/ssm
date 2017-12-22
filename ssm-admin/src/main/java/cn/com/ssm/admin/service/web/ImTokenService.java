package cn.com.ssm.admin.service.web;

import cn.com.ssm.common.mapper.ImTokenMapper;
import cn.com.ssm.common.model.ImToken;
import com.ivymei.framework.system.base.BaseService;

/**
 * Created by Nathy on 2017/12/11.
 */
public interface ImTokenService extends BaseService<ImToken, ImTokenMapper> {
    ImToken get();

}
