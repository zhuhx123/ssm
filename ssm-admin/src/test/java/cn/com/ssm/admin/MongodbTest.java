package cn.com.ssm.admin;

import cn.com.ssm.admin.component.job.TokenDao;
import cn.com.ssm.admin.service.web.ImTokenService;
import cn.com.ssm.common.model.mongo.MBReportToken;
import cn.com.ssm.common.mongo.MongoDbBaseDao;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathy on 2017/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/applicationContext.xml", "classpath:configs/spring/springMVC-servlet.xml"})
public class MongodbTest {
    private Logger logger = Logger.getLogger(MongodbTest.class);

    @Autowired
    ImTokenService imTokenService;

    @Autowired
    TokenDao tokenDao;

    @Autowired
    MongoDbBaseDao mongoDbBaseDao;
    @org.junit.Test
    public void insert() {
        MBReportToken token=new MBReportToken();
        token.setMobile(imTokenService.selectByPrimaryKey(2).getMobile());
        token.setId("2");
        token.setKey("key_2");
        tokenDao.insertToken(token);
    }

    @org.junit.Test
    public void select1(){
        System.out.print(imTokenService.selectByPrimaryKey(1).getMobile());
    }

    @org.junit.Test
    public void select(){
        List<MBReportToken> token=new ArrayList<MBReportToken>();
        token=tokenDao.listToken();
        System.out.print(token.get(0).getKey());
    }
    @org.junit.Test
    public void selectById(){
        MBReportToken token=new MBReportToken();
        token=tokenDao.selectById("2");
        System.out.print(token.getKey());
    }

    @org.junit.Test
    public void selectById1(){
        MBReportToken token=new MBReportToken();
        token=mongoDbBaseDao.findById("2",MBReportToken.class);
        System.out.print(token.getKey());
    }

}
