package cn.com.ssm.admin;

import cn.com.ssm.admin.service.web.ImTokenService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Nathy on 2017/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:configs/spring/applicationContext.xml"})
public class SpringMVCMybatisTest {
    @Autowired
    ImTokenService imTokenService;

    @org.junit.Test
    public void test1() {
        System.out.print(imTokenService.selectByPrimaryKey(2).gettId());
    }
}
