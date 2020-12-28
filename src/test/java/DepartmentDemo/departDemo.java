package DepartmentDemo;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author wangqian
 * @Date 2020-12-23 14:57
 * @Version 1.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class departDemo {
    static String access_token;
    static String dapartmentId;
    @BeforeAll
    public static void demo1() {
        access_token = given()
                .when()
                .param("corpid", "ww4051b286f081f407")
                .param("corpsecret", "FMkwsTRmKSqffzFikrBWzqhsN8_dl0F7_SMvPfRWEkQ")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .extract().response().path("access_token");
        //System.out.println(access_token);
    }

    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment(){
        String body = "    {\n" +
                "       \"name\": \"州000\",\n" +
                "       \"name_en\": \"2001\",\n" +
                "       \"parentid\": 1,\n" +
                "    }";
        Response response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + access_token + "")
                .then().log().all()
                .extract()
                .response();
        dapartmentId = response.path("id").toString();
        System.out.println(dapartmentId);

    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment(){
        String body = "    {\n" +
                "       \"id\": "+dapartmentId+",\n" +
                "       \"name\": \"广州研发中心\",\n" +
                "       \"name_en\": \"RDGZ\",\n" +
                "       \"parentid\": 1,\n" +
                "       \"order\": 1\n" +
                "    }";
        Response response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+access_token+"")
                .then().log().all()
                .extract()
                .response();
         assertEquals("0",response.path("errcode").toString());

    }
    @DisplayName("查询部门")
    @Test
    @Order(3)
    void searchDepartment(){
        Response response = given().log().all()
                .contentType("application/json")
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + access_token + "&id=" + dapartmentId + "")
                .then().log().all()
                .extract()
                .response();
        assertEquals("0",response.path("errcode").toString());
        assertEquals(dapartmentId,response.path("department.id[0]").toString());

    }
    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment(){
        Response response = given().log().all()
                .contentType("application/json")
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=" + access_token + "&id=" + dapartmentId + "")
                .then().log().all()
                .extract()
                .response();
        assertEquals("0",response.path("errcode").toString());

    }
}
