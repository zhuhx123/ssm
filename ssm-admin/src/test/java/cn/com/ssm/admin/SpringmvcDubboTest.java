package cn.com.ssm.admin;

import cn.com.ssm.admin.service.web.provider.TestService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Nathy on 2017/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:configs/spring/applicationContext.xml"})
public class SpringmvcDubboTest {
    @Autowired
    TestService testService;

    @org.junit.Test
    public void test(){
        System.out.print(testService.s());
    }
}
