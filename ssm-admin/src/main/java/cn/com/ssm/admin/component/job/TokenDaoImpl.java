package cn.com.ssm.admin.component.job;

import cn.com.ssm.common.model.mongo.MBReportToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nathy on 2017/12/12.
 */
@Service
public class TokenDaoImpl implements TokenDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<MBReportToken> listToken() {
        return this.mongoTemplate.find(new Query(), MBReportToken.class);
    }

    public void insertToken(MBReportToken token) {
        this.mongoTemplate.insert(token);
    }

    public MBReportToken selectById(String id) {
       return this.mongoTemplate.findOne(new Query(new Criteria().where("id").is(id)),MBReportToken.class);
    }
}
