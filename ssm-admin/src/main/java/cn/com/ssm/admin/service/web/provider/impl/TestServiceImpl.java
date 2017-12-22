package cn.com.ssm.admin.service.web.provider.impl;

import cn.com.ssm.admin.service.web.provider.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by Nathy on 2017/12/15.
 */
@Service
public class TestServiceImpl implements TestService {
    public String s() {
        System.out.print(76476);
        return "111111111";
    }
}
