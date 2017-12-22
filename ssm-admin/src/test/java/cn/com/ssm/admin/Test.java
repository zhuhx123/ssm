package cn.com.ssm.admin;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Administrator on 2017/12/10 0010.
 */
public class Test {
    public static void main(String args[]){
        try {
            Reader reader = Resources.getResourceAsReader("configs/mybatis/mybatis.xml");
            SqlSessionFactory sqlFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



