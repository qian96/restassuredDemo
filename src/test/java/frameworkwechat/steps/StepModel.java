package frameworkwechat.steps;


import Utils.PlaceholderUtils;
import frameworkwechat.global.ApiLoader;
import frameworkwechat.global.GlobalVariables;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @Author wangqian
 * @Date 2020-12-31 17:19
 * @Version 1.0
 */
public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);

    //1.定义assertModel类
    private String api;
    private String action;
    private ArrayList<String> actualParameter;

    private ArrayList<AssertModel> asserts;
    private HashMap<String,String> save;
    private HashMap<String,String> saveGlobal;

    //2.定义stepResult类
    private ArrayList<String> finalActualParater = new ArrayList<>();
    private HashMap<String,String> stepVaraiables = new HashMap<>();
    private StepResult stepResult = new StepResult();
    private ArrayList<Executable> assertList = new ArrayList<>();

    public ArrayList<String> getFinalActualParater() {
        return finalActualParater;
    }

    public void setFinalActualParater(ArrayList<String> finalActualParater) {
        this.finalActualParater = finalActualParater;
    }

    public HashMap<String, String> getStepVaraiables() {
        return stepVaraiables;
    }

    public void setStepVaraiables(HashMap<String, String> stepVaraiables) {
        this.stepVaraiables = stepVaraiables;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(ArrayList<String> actualParameter) {
        this.actualParameter = actualParameter;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

   public StepResult run(HashMap<String,String> testCaseVariables){
        //定义assertModel类
       if(actualParameter != null){
            finalActualParater.addAll(PlaceholderUtils.resolveList(actualParameter,testCaseVariables));
       }
       //根据case中配置得api对象和action信息，取出并执行相应得action
       Response response = ApiLoader.getAction(api,action).run(finalActualParater);
       //存储save
       if(save != null){
           save.forEach((variablesName,path)->{
               String value = response.path(path).toString();
               stepVaraiables.put(variablesName,value);
                logger.info("step变量更新"+stepVaraiables);
           });
       }
       //存储全局变量得save、
       if(saveGlobal != null){
            saveGlobal.forEach((variablesName,path)->{
                String value = response.path(path).toString();
                GlobalVariables.getGlobalVariables().put(variablesName,value);
                logger.info("全局变量更新"+GlobalVariables.getGlobalVariables());
            });
       }

       //根据case中得配置对返回结果进行软断言，
       if(asserts != null){
           asserts.stream().forEach(assertModel -> {
               assertList.add(()->{
                  assertThat(
                          assertModel.getReason(),
                          response.path(assertModel.getActual()).toString(),
                          equalTo(assertModel.getExpect()));
               });
           });
       }
       //将response和断言结果存储到stepResult对象中并返回
        stepResult.setAssertList(assertList);
        stepResult.setStepsVariables(stepVaraiables);
        stepResult.setResponse(response);

       return stepResult;
   }
}
