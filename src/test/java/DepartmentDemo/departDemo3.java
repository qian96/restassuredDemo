package DepartmentDemo;

import Base.DepartmentObject;
import Base.GetToken;
import Utils.TimeUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author wangqian
 * @Date 2020-12-23 14:57
 * @Version 1.0
 */

public class departDemo3 {
    static String access_token;

    @BeforeAll
    public static void demo1() {
        access_token = GetToken.getToken();
    }

    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/createDepartment.csv",numLinesToSkip = 1)
    void createDepartment(String name,String NameEn, String returncode){
        Response response = DepartmentObject.createDepartment(name,NameEn,access_token);
        assertEquals(returncode,response.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    void searchDepartment(){
        String newName = TimeUtil.getCurrentUnixTime();
        String newNameEn = TimeUtil.getRandMem();
        Response createresponse = DepartmentObject.createDepartment(newName,newNameEn,access_token);
        String departid = createresponse.path("id").toString();
        Response response = DepartmentObject.searchDepartment(access_token, departid);

       /* assertEquals("0",response.path("errcode").toString());
        assertEquals(departid,response.path("department.id[0]").toString());
        assertEquals(newName,response.path("department.name[0]").toString());
        assertEquals(newNameEn,response.path("department.name_en[0]").toString());*/

        assertAll("返回值",
                 ()->assertEquals("0",response.path("errcode").toString()),
                 ()-> assertEquals(departid+"2",response.path("department.id[0]").toString()),
                 ()->assertEquals(newName+00,response.path("department.name[0]").toString()),
                 ()->assertEquals(newNameEn,response.path("department.name_en[0]").toString())
        );
    }

}
