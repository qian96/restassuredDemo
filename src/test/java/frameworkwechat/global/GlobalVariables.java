package frameworkwechat.global;

import java.util.HashMap;

/**
 * @Author wangqian
 * @Date 2020-12-29 15:00
 * @Version 1.0
 */
public class GlobalVariables {
  private static HashMap<String,String> globalVariables = new HashMap<>();

    public static HashMap<String, String> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, String> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }
}
