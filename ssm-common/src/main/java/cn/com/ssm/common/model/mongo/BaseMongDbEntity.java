package cn.com.ssm.common.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by 20170331 on 2017/6/30.
 */
public class BaseMongDbEntity {


    @Id
    private String id;

    @Indexed(unique = true)
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
