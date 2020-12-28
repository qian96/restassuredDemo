package Base;

import static io.restassured.RestAssured.given;

/**
 * @Author wangqian
 * @Date 2020-12-24 20:58
 * @Version 1.0
 */
public class GetToken {

    public static String getToken(){
       String access_token = given()
                .when()
                .param("corpid", "ww4051b286f081f407")
                .param("corpsecret", "FMkwsTRmKSqffzFikrBWzqhsN8_dl0F7_SMvPfRWEkQ")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .extract().response().path("access_token");
        return access_token;
    }
}
