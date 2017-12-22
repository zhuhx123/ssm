package cn.com.ssm.admin.service.web.impl;

import cn.com.ssm.admin.service.web.ImTokenService;
import cn.com.ssm.common.mapper.ImTokenMapper;
import cn.com.ssm.common.model.ImToken;
import com.ivymei.framework.system.base.BaseMapper;
import com.ivymei.framework.system.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Nathy on 2017/12/11.
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service
public class ImTokenServiceImpl extends BaseServiceImpl<ImToken ,ImTokenMapper> implements ImTokenService {
    @Autowired
    ImTokenMapper imTokenMapper;

    public ImToken get(){
        return imTokenMapper.selectByPrimaryKey(1);
    }

    @Override
    public BaseMapper<ImToken, ImTokenMapper> getBaseMapper() {
        return imTokenMapper;
    }
}
