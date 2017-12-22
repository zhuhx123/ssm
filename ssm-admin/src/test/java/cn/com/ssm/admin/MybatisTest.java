package cn.com.ssm.admin;


import cn.com.ssm.common.mapper.ImTokenMapper;
import cn.com.ssm.common.model.ImToken;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Nathy on 2017/12/11.
 */
public class MybatisTest {
    private static SqlSessionFactory sqlFactory;
    private static Reader reader;
    @Before
    public void init(){
        try {
            reader = Resources.getResourceAsReader("configs/mybatis/mybatis.xml");
            sqlFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void select() {
        SqlSession session=sqlFactory.openSession();
        ImTokenMapper token=session.getMapper(ImTokenMapper.class);
        System.out.print(token.selectByPrimaryKey(1).getMobile());
    }

    public void select2(){

    }
}
