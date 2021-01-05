package frameworkwechat.steps;


import io.restassured.response.Response;

/**
 * @Author wangqian
 * @Date 2021-01-04 15:06
 * @Version 1.0
 */
public class BaseResult {
   public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
