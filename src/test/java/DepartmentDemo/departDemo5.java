package DepartmentDemo;

import Base.DepartmentObject;
import Base.GetToken;
import Utils.TimeUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author wangqian
 * @Date 2020-12-23 14:57
 * @Version 1.0
 */
@Epic("企业微信接口测试用例")
@Feature("部门接口功能测试用例")
public class departDemo5 {
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
    @Description("创建部门-入参数据驱动")
    @Story("创建部门测试")
    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/createDepartment.csv",numLinesToSkip = 1)
    void createDepartment(String name,String NameEn,String returncode){
        Response response = DepartmentObject.createDepartment(name,NameEn,access_token);
        assertEquals(returncode,response.path("errcode").toString());

    }

    @Description("修改部门-入参数据驱动")
    @Story("修改部门测试")
    @DisplayName("修改部门")
    @Test
    void updateDepartment(){
        String dapartmentId = DepartmentObject.createDepartment(access_token);
        String newName = TimeUtil.getCurrentUnixTime();
        String newNameEn = TimeUtil.getRandMem();
        Response response = DepartmentObject.updateDepartment(access_token,dapartmentId,newName,newNameEn);
        assertEquals("0",response.path("errcode").toString());

    }
    @Description("查询部门-入参数据驱动")
    @Story("查询部门测试")
    @DisplayName("查询部门")
    @Test
    void searchDepartment(){
        String dapartmentId = DepartmentObject.createDepartment(access_token);
        Response response = DepartmentObject.searchDepartment(access_token,dapartmentId);
        assertEquals("0",response.path("errcode").toString());
        assertEquals(dapartmentId,response.path("department.id[0]").toString());

    }
    @Description("删除部门-入参数据驱动")
    @Story("删除部门测试")
    @DisplayName("删除部门")
    @Test
    void deleteDepartment(){
        String dapartmentId = DepartmentObject.createDepartment(access_token);
        Response response = DepartmentObject.deleteDepartment(access_token,dapartmentId);
        assertEquals("0",response.path("errcode").toString());

    }
}
