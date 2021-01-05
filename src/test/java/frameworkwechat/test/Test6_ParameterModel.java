package frameworkwechat.test;

import frameworkwechat.global.ApiLoader;
import frameworkwechat.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Author wangqian
 * @Date 2020-12-29 16:04
 * @Version 1.0
 */
public class Test6_ParameterModel {
    public static final Logger logger = LoggerFactory.getLogger(Test6_ParameterModel.class);
   @ParameterizedTest(name = "{index} {1}")
   @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel,String name,String description){
        logger.info("用例执行");
        logger.info("用例名称"+name);
        logger.info("用例描述"+description);
        apiTestCaseModel.run();
    }
    static List<Arguments> apiTest(){
       ApiLoader.load("src/test/resources/api");
       //传递参数化用例
        List<Arguments> testcase = new ArrayList<>();
        //读取所有用例
        String testcasrDir= "src/test/resources/testcast";
        for (String name : new File(testcasrDir).list()) {
            String path = testcasrDir + "/" + name;
            try {
                ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load(path);

                testcase.add(Arguments.arguments(apiTestCaseModel,apiTestCaseModel.getName(),apiTestCaseModel.getDescription()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return testcase;
    }
}
