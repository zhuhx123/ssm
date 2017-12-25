package cn.com.ssm.wechat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Nathy on 2017/12/25.
 */
public class JsonUtil {


    public static JsonNode objectToJsonNode(Object obj){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String objJson=objectMapper.writeValueAsString(obj);
            JsonNode jsonNode = objectMapper.readTree(objJson);
            return jsonNode;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
