package frameworkwechat.steps;


import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author wangqian
 * @Date 2020-12-31 17:29
 * @Version 1.0
 */
public class StepResult extends BaseResult{
    /**
     * 定义一个类存储每个step执行的一个结果返回去的值一个类
     * 存每一个step断言的中间的一个对象 Executable
     */
    private ArrayList<Executable> assertList;
    private HashMap<String,String> stepsVariables = new HashMap<>();

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getStepsVariables() {
        return stepsVariables;
    }

    public void setStepsVariables(HashMap<String, String> stepsVariables) {
        this.stepsVariables = stepsVariables;
    }
}
