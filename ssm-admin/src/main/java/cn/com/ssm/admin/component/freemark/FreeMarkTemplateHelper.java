package cn.com.ssm.admin.component.freemark;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.IOException;

/**
 * Created by Nathy on 2017/12/12.
 */
public class FreeMarkTemplateHelper {
    //创建一个合适的Configration对象
    private Configuration configuration = new Configuration(new Version("2.3.23"));
    //设置模板文件的基路径
    private String templateDir="/tpl";
    private String charset="UTF-8";

    private static FreeMarkTemplateHelper instance;

    public static FreeMarkTemplateHelper getInstance(){
        if(instance==null){
            instance=new FreeMarkTemplateHelper();
        }
        return instance;
    }

    private FreeMarkTemplateHelper() {
        configuration.setClassLoaderForTemplateLoading(FreeMarkTemplateHelper.class.getClassLoader(),templateDir);
        configuration.setCacheStorage(new freemarker.cache.MruCacheStorage(0, 0));
    }

    /**
     * 获取模板 ，支持文件夹形式
     * @param templateName
     * @return
     * @throws IOException
     */
    public Template getTemplate(String templateName) throws IOException {
        Template template=configuration.getTemplate(templateName,charset);
        return template;
    }
}
