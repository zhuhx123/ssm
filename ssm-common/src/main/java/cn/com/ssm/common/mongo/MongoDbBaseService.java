package cn.com.ssm.common.mongo;

import com.mongodb.DBCollection;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Nathy on 2017/12/13.
 */
@Service
public class MongoDbBaseService {
    @Resource
    private MongoDbBaseDao mongoDbBaseDao;


    public List<T> query(Query query, Class<T> clazz) {
        return mongoDbBaseDao.query(query, clazz);
    }

    public <T> void saveOrUpdate(T t) {
        mongoDbBaseDao.save(t);
    }

    public <T> T findById(Object id, Class<T> clazz) {
        return mongoDbBaseDao.findById(id, clazz);
    }

    public <T> Long count(Query query, Class<T> clazz) {
        return mongoDbBaseDao.count(query, clazz);
    }

    public DBCollection getCollection(String name) {
        return mongoDbBaseDao.getCollection(name);
    }

    public MongoTemplate getMongoTemplate() {
        return mongoDbBaseDao.getMongoTemplate();
    }

}
