package Base;

import Utils.TimeUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author wangqian
 * @Date 2020-12-24 21:00
 * @Version 1.0
 */
public class DepartmentObject {
    public static Response createDepartment(String newName,String newNameEn,String access_token) {
        String createbody = "    {\n" +
                "       \"name\": \"" + newName + "\",\n" +
                "       \"name_en\": \"" + newNameEn + "\",\n" +
                "       \"parentid\": 1,\n" +
                "       \"order\": 1,\n" +
                "    }";
        Response createresponse = given().log().all()
                .contentType("application/json")
                .body(createbody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + access_token + "")
                .then().log().all()
                .extract()
                .response();
        return createresponse;
    }

    public static String createDepartment(String access_token) {
        String newName = TimeUtil.getCurrentUnixTime();
        String newNameEn = TimeUtil.getRandMem();
        Response response = createDepartment(newName, newNameEn, access_token);
        String dapartmentId = response.path("id").toString();
        return dapartmentId;
    }

    public static String createDepartment2(String access_token) {
        String newName =Thread.currentThread().getId()+TimeUtil.getCurrentUnixTime();
        String newNameEn = Thread.currentThread().getId()+TimeUtil.getRandMem();
        Response response = createDepartment(newName, newNameEn, access_token);
        String dapartmentId = response.path("id").toString();
        return dapartmentId;
    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    public static Response updateDepartment(String access_token,String dapartmentId,String newName,String newNameEn){
        String updatebody = "    {\n" +
                "       \"id\": "+dapartmentId+",\n" +
                "       \"name\": \""+newName+"\",\n" +
                "       \"name_en\": \""+newNameEn+"\",\n" +
                "       \"parentid\": 1,\n" +
                "       \"order\": 1\n" +
                "    }";
        Response updateresponse = given().log().all()
                .contentType("application/json")
                .body(updatebody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+access_token+"")
                .then().log().all()
                .extract()
                .response();
       return  updateresponse;
    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    public static Response searchDepartment(String access_token,String dapartmentId){
        Response response = given().log().all()
                .contentType("application/json")
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + access_token + "&id=" + dapartmentId + "")
                .then().log().all()
                .extract()
                .response();
        return response;
    }
    @DisplayName("查询部门")
    @Test
    @Order(3)
    public static Response listDepartment(String access_token,String departmentId){
        Response response = given().log().all()
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+access_token+"")
                .then()
                .log().body()
                .extract()
                .response();
        return response;
    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    public static Response deleteDepartment(String access_token,String dapartmentId){
        Response response = given().log().all()
                .contentType("application/json")
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=" + access_token + "&id=" + dapartmentId + "")
                .then().log().all()
                .extract()
                .response();
        return response;

    }
    public static Response delete(String access_token,String departId){
        Response response = given().log().all()
                .param("access_token", access_token)
                .param("id", departId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract().response();
        return response;
    }

}
