package frameworkwechat.actions;

import Utils.PlaceholderUtils;
import frameworkwechat.global.GlobalVariables;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @Author wangqian
 * @Date 2020-12-29 14:47
 * @Version 1.0
 */

public class ApiActionModel {
    private String method="get";
    private String url;
    private String body;
    private String contentType;
    private HashMap<String,String> query;
    private HashMap<String,String> headers;
    private String post;
    private String get;
    private Response response;
    private ArrayList<String> formalParam;

    private HashMap<String,String> actionVariables = new HashMap<>();

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> query) {
        this.query = query;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public ArrayList<String> getFormalParam() {
        return formalParam;
    }

    public void setFormalParam(ArrayList<String> formalParam) {
        this.formalParam = formalParam;
    }

    public HashMap<String, String> getActionVariables() {
        return actionVariables;
    }

    public void setActionVariables(HashMap<String, String> actionVariables) {
        this.actionVariables = actionVariables;
    }

    public Response run(ArrayList<String> actualParam){
        //存储变量结果
        HashMap<String,String> finalQuery = new HashMap<>();
        String runBody = this.body;
        String runUrl = this.url;
      //确定请求方法和URL
        if(post != null){
            runUrl=post;
            method = "post";
        }else if(get != null){
            runUrl=get;
            method = "get";
        }
        //请求参数，URL中全局变量替换，这里需要编写占位符工具了
        if(query != null){
            finalQuery.putAll(PlaceholderUtils.resolveMap(query, GlobalVariables.getGlobalVariables()));
        }
        //body全局变量替换
        runBody = PlaceholderUtils.resolveString(runBody,GlobalVariables.getGlobalVariables());
        //url全局变量替换
        runUrl = PlaceholderUtils.resolveString(runUrl,GlobalVariables.getGlobalVariables());

        if(formalParam != null && actualParam != null && formalParam.size() >0 && actualParam.size() >0){
            //根据形参和实参构建内部变量
            for(int index=0;index < formalParam.size();index++){
                actionVariables.put(formalParam.get(index),actualParam.get(index));
            }
            //请求，url中的内部变量替换
            if(query != null){
                finalQuery.putAll(PlaceholderUtils.resolveMap(query,actionVariables));
            }
            runBody=  PlaceholderUtils.resolveString(runBody,actionVariables);
            runUrl = PlaceholderUtils.resolveString(runUrl,actionVariables);
        }
        //拿到上面完成了变量替换的请求数据，然后进行请求并返回结果

        RequestSpecification requestSpecification = given().log().all();
        if(contentType != null){
            requestSpecification.contentType(contentType);
        }
        if(headers != null){
            requestSpecification.headers(headers);
        }
        if(finalQuery != null && finalQuery.size() >0){
           requestSpecification.formParams(finalQuery);
        }else if(runBody != null){
            requestSpecification.body(runBody);
        }

        Response response = requestSpecification.request(method, runUrl).then().log().all().extract().response();
        this.response = response;
        return response;
    }

}
