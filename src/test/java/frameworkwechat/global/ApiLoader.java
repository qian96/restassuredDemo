package frameworkwechat.global;

import frameworkwechat.actions.ApiActionModel;
import frameworkwechat.api.ApiObjectModel;
import frameworkwechat.test.Test1_ApiActionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangqian
 * @Date 2020-12-31 14:31
 * @Version 1.0
 */
public class ApiLoader {
    public static final Logger logger = LoggerFactory.getLogger(Test1_ApiActionModel.class);
    //加载所有api object对象，并保存到本地列表
    private static List<ApiObjectModel> apis = new ArrayList<>();
    public static void  load(String dir){
        Arrays.stream(new File(dir).list()).forEach(path->{
            try {
                apis.add(ApiObjectModel.load(dir + "/" + path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    //封装一个getaction
    public static ApiActionModel getAction(String apiName,String actionName){
        final ApiActionModel[] apiActionModel = {new ApiActionModel()};

        apis.stream().filter(api -> api.getName().equals(apiName)).forEach(api -> {
            apiActionModel[0] =api.getActions().get(actionName);
        });
        if(apiActionModel[0] != null){
            return apiActionModel[0];
        }else{
           logger.info("没有找到接口");
        }
        return null;
    }
}
