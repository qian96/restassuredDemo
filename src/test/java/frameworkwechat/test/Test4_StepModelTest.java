package frameworkwechat.test;

import frameworkwechat.global.ApiLoader;
import frameworkwechat.steps.AssertModel;
import frameworkwechat.steps.StepModel;
import org.junit.jupiter.api.BeforeAll;
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
public class Test4_StepModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test4_StepModelTest.class);
    @BeforeAll
    static void loadTest() throws Exception {
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger");

    }
    @Test
    void runTest(){
        //实参
        ArrayList<String> actualParamter =new ArrayList<>();
        actualParamter.add("ww4051b286f081f407");
        actualParamter.add("FMkwsTRmKSqffzFikrBWzqhsN8_dl0F7_SMvPfRWEkQ");

        //断言
        ArrayList<AssertModel> asserts = new ArrayList<>();
        AssertModel assertModel = new AssertModel();
        assertModel.setActual("errcode");
        assertModel.setExpect("0");
        assertModel.setMatcher("equalTo");
        assertModel.setReason("getToken错误码校验01");
        asserts.add(assertModel);

        //save
        HashMap<String,String> save = new HashMap<>();
        save.put("accesstoken","access_token");

        //globalsave
        HashMap<String,String> globalsave = new HashMap<>();
        globalsave.put("accesstoken","access_token");

        StepModel stepModel = new StepModel();
        stepModel.setApi("tokenhelper");
        stepModel.setAction("getToken");
        stepModel.setActualParameter(actualParamter);
        stepModel.setAsserts(asserts);
        stepModel.setSaveGlobal(globalsave);
        stepModel.setSave(save);

        stepModel.run(null);

        logger.info("Debug!");

    }
}
