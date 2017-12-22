package cn.com.ssm.admin.controller;

import cn.com.ssm.admin.service.web.ImTokenService;
import cn.com.ssm.common.model.ImToken;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Nathy on 2017/12/11.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    ImTokenService imTokenService;

    @RequestMapping("/get")
    @ResponseBody
    public String get(){
        ImToken token=imTokenService.selectByPrimaryKey(1);
        JSONObject obj=new JSONObject();
        obj.put("data",token);
        return obj.toString();
    }

}
