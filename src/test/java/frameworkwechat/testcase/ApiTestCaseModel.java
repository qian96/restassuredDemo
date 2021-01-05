package frameworkwechat.testcase;

import Utils.FakerUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import frameworkwechat.steps.StepModel;
import frameworkwechat.steps.StepResult;

import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @Author wangqian
 * @Date 2021-01-04 16:30
 * @Version 1.0
 */
public class ApiTestCaseModel {
    public static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModel.class);

    private String name;
    private String description;
    private ArrayList<StepModel> steps;
    private ArrayList<Executable> assertList = new ArrayList<>();
    private HashMap<String,String> testcaseVariables = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getTestcaseVariables() {
        return testcaseVariables;
    }

    public void setTestcaseVariables(HashMap<String, String> testcaseVariables) {
        this.testcaseVariables = testcaseVariables;
    }


    public static ApiTestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiTestCaseModel.class);
    }

    public void run(){
        //加载用例层关键字变量
        this.testcaseVariables.put("getTimeStamp", FakerUtils.getTimeStamp());
        logger.info("用例变量更新"+testcaseVariables);
        //遍历step进行执行
        steps.forEach(step->{
            StepResult stepResult = step.run(testcaseVariables);
            if(stepResult.getStepsVariables().size()>0){
                testcaseVariables.putAll(stepResult.getStepsVariables());
                logger.info("testcase变量更新"+testcaseVariables);
            }
            //处理assertList，进行统一断言
            if(stepResult.getAssertList().size()>0){
                assertList.addAll(stepResult.getAssertList());
            }
        });
        //进行统一断言
        assertAll("",assertList.stream());




    }

}
