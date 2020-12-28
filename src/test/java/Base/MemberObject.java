package Base;


import Utils.TimeUtil;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * @Author wangqian
 * @Date 2020-12-25 14:24
 * @Version 1.0
 */
public class MemberObject {
    public static Response creatMenber(String access_token,String userId,String userName,String email,String telephone){
        String creatBody = "    {\n" +
                "        \"userid\": \""+userId+"\",\n" +
                "         \"department\": \"1\",\n" +
                "         \"email\": \""+email+"\",\n" +
                "         \"telephone\": \""+telephone+"\",\n" +
                "        \"name\": \""+userName+"\"\n"
                ;

        Response response = given()
                .log().all()
                .contentType("application/json")
                .body(creatBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + access_token + "")
                .then().log().all()
                .extract()
                .response();
        return  response;
    }

    public static String getMemberId(String access_token){
        String userId = TimeUtil.getRandMem();
        String userName = TimeUtil.getUserName();
        String telephone = TimeUtil.getTel();
        String email = TimeUtil.getEmail();
        Response response = creatMenber(access_token, userId, userName, email, telephone);
        return userId;
    }

    public static Response updateMember(String access_token,String userId,String updateName){
         String updateBody ="{\n" +
                "    \"userid\": \""+userId+"\",\n" +
                "    \"name\": \""+updateName+"\"" +
                "}";
        Response updateresponse = given()
                .log().all()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+access_token+"")
                .then().log().all()
                .extract()
                .response();
        return  updateresponse;
    }
    public static Response listMember(String access_token){
        Response re = given()
                .log().all()
                .contentType("application/json")
                .param("department_id", 1)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + access_token + "")
                .then().log().all()
                .extract().response();
        return re;
    }
    public static Response deleteMember(String access_token,String userId){
        Response deleteresponse = given()
                .log().all()
                .contentType("application/json")
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" + access_token + "&userid=" + userId + "")
                .then().log().all()
                .extract().response();
        return deleteresponse;

    }
}
