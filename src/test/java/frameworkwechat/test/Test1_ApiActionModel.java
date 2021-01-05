package frameworkwechat.test;

import Utils.PlaceholderUtils;
import frameworkwechat.actions.ApiActionModel;
import frameworkwechat.global.GlobalVariables;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * @Author wangqian
 * @Date 2020-12-29 16:04
 * @Version 1.0
 */
public class Test1_ApiActionModel {
    public static final Logger logger = LoggerFactory.getLogger(Test1_ApiActionModel.class);
    @Test
    void runTest(){
        ArrayList<String> actualParamter =new ArrayList<>();
        actualParamter.add("ww4051b286f081f407");
        actualParamter.add("FMkwsTRmKSqffzFikrBWzqhsN8_dl0F7_SMvPfRWEkQ");

        ApiActionModel apiActionModel = new ApiActionModel();
        apiActionModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");
        HashMap<String,String> globalVariables = new HashMap<>();

        globalVariables.put("x","gettoken");
        GlobalVariables.setGlobalVariables(globalVariables);

        ArrayList<String> formalPatameter = new ArrayList<>();
        formalPatameter.add("corpid");
        formalPatameter.add("corpsecret");
        apiActionModel.setFormalParam(formalPatameter);

        HashMap<String,String> query = new HashMap<>();
        query.put("corpid","${corpid}");
        query.put("corpsecret","${corpsecret}");

        apiActionModel.setQuery(query);

        Response run = apiActionModel.run(actualParamter);

    }
}
