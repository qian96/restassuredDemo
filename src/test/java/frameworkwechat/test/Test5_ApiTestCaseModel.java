package frameworkwechat.test;

import frameworkwechat.global.ApiLoader;
import frameworkwechat.steps.AssertModel;
import frameworkwechat.steps.StepModel;
import frameworkwechat.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @Author wangqian
 * @Date 2020-12-29 16:04
 * @Version 1.0
 */
public class Test5_ApiTestCaseModel {
    public static final Logger logger = LoggerFactory.getLogger(Test5_ApiTestCaseModel.class);
    @BeforeAll
    static void loadTest() throws Exception {
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger");

    }

    @Test
    void runTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/test/resources/testcast/creatdepartment.yaml");

        apiTestCaseModel.run();
        logger.info("debugger");
    }
}
