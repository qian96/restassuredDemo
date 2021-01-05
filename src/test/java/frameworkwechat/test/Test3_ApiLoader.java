package frameworkwechat.test;

import frameworkwechat.api.ApiObjectModel;
import frameworkwechat.global.ApiLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


/**
 * @Author wangqian
 * @Date 2020-12-29 16:04
 * @Version 1.0
 */
public class Test3_ApiLoader {
    public static final Logger logger = LoggerFactory.getLogger(Test3_ApiLoader.class);
    @BeforeAll
    static void loadTest() throws Exception {
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger");

    }
    @Test
    void getAction(){
        ArrayList<String> actualParamter =new ArrayList<>();
        actualParamter.add("ww4051b286f081f407");
        actualParamter.add("FMkwsTRmKSqffzFikrBWzqhsN8_dl0F7_SMvPfRWEkQ");
        ApiLoader.getAction("tokenhelper","getToken").run(actualParamter);

    }
}
