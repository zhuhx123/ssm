package cn.com.ssm.common.mongo;

import com.mongodb.DBCollection;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nathy on 2017/12/13.
 */
@Repository
public class MongoDbBaseDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> void save(T t) {
        this.mongoTemplate.save(t);
    }

    public List<T> query(Query query, Class<T> clazz) {
        return this.mongoTemplate.find(query, clazz);
    }

    public <T> void saveOrUpdate(T t) {
        this.mongoTemplate.save(t);
    }

    public <T> T findById(Object id, Class<T> clazz) {
        return this.mongoTemplate.findById(id, clazz);
    }

    public <T> Long count(Query query, Class<T> clazz) {
        return this.mongoTemplate.count(query, clazz);
    }

    public DBCollection getCollection(String name) {
        return this.mongoTemplate.getCollection(name);
    }

    public MongoTemplate getMongoTemplate() {
        return this.mongoTemplate;
    }

}
