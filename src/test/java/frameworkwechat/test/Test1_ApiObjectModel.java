package frameworkwechat.test;

import frameworkwechat.actions.ApiActionModel;
import frameworkwechat.api.ApiObjectModel;
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
public class Test1_ApiObjectModel {
    public static final Logger logger = LoggerFactory.getLogger(Test1_ApiObjectModel.class);
    @Test
    void runTest() throws Exception {
        ArrayList<String> actualParamter =new ArrayList<>();
        actualParamter.add("ww4051b286f081f407");
        actualParamter.add("FMkwsTRmKSqffzFikrBWzqhsN8_dl0F7_SMvPfRWEkQ");

        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/api/tokenhelper.yaml");
        apiObjectModel.getActions().get("getToken").run(actualParamter);

    }
}
