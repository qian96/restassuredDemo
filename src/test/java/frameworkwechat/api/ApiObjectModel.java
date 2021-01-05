package frameworkwechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import frameworkwechat.actions.ApiActionModel;

import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;

import java.io.File;
import java.util.HashMap;

/**
 * @Author wangqian
 * @Date 2020-12-31 13:59
 * @Version 1.0
 */
public class ApiObjectModel {
    private String name;
    private HashMap<String, ApiActionModel> actions;
    private HashMap<String,String> abVariables = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ApiActionModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, ApiActionModel> actions) {
        this.actions = actions;
    }

    public HashMap<String, String> getAbVariables() {
        return abVariables;
    }

    public void setAbVariables(HashMap<String, String> abVariables) {
        this.abVariables = abVariables;
    }

    public static  ApiObjectModel load(String path) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiObjectModel.class);
    }
}
