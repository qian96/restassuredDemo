package DepartmentDemo;

import Base.DepartmentObject;
import Base.GetToken;
import Utils.TimeUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author wangqian
 * @Date 2020-12-23 14:57
 * @Version 1.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class departDemo2 {
    static String access_token;

    @BeforeAll
    public static void demo1() {
        access_token = GetToken.getToken();
    }

    //每次运行后清除数据
    @AfterEach
    @BeforeEach
    void clearDepartment(){
        Response listresponse = DepartmentObject.listDepartment(access_token,"1");
        ArrayList<Integer> departListId = listresponse.path("department.id");
        for (int departId : departListId){
            if(1==departId){
                continue;
            }
            DepartmentObject.delete(access_token,departId+"");
        }

    }
    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment(){
        String newName = TimeUtil.getCurrentUnixTime();
        String newNameEn = TimeUtil.getRandMem();
        Response response = DepartmentObject.createDepartment(newName,newNameEn,access_token);
        assertEquals("0",response.path("errcode").toString());

    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment(){
        String dapartmentId = DepartmentObject.createDepartment(access_token);
        String newName = TimeUtil.getCurrentUnixTime();
        String newNameEn = TimeUtil.getRandMem();
        Response response = DepartmentObject.updateDepartment(access_token,dapartmentId,newName,newNameEn);
        assertEquals("0",response.path("errcode").toString());

    }
    @DisplayName("查询部门")
    @Test
    @Order(3)
    void searchDepartment(){
        String dapartmentId = DepartmentObject.createDepartment(access_token);
        Response response = DepartmentObject.searchDepartment(access_token,dapartmentId);
        assertEquals("0",response.path("errcode").toString());
        assertEquals(dapartmentId,response.path("department.id[0]").toString());

    }
    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment(){
        String dapartmentId = DepartmentObject.createDepartment(access_token);
        Response response = DepartmentObject.deleteDepartment(access_token,dapartmentId);
        assertEquals("0",response.path("errcode").toString());

    }
}
