package DepartmentDemo;

import Base.DepartmentObject;
import Base.GetToken;
import Utils.TimeUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author wangqian
 * @Date 2020-12-23 14:57
 * @Version 1.0
 */
public class departDemo4 {
    static String access_token;

    @BeforeAll
    public static void demo1() {
        access_token = GetToken.getToken();
    }

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
    @RepeatedTest(10)
    void createDepartment(){
        String newName = TimeUtil.getCurrentUnixTime();
        String newNameEn = TimeUtil.getRandMem();
        Response response = DepartmentObject.createDepartment(newName,newNameEn,access_token);
        assertEquals("0",response.path("errcode").toString());

    }

    @DisplayName("修改部门")
    @RepeatedTest(10)
    void updateDepartment(){
        String dapartmentId = DepartmentObject.createDepartment2(access_token);
        String newName = Thread.currentThread().getId()+TimeUtil.getCurrentUnixTime();
        String newNameEn = Thread.currentThread().getId()+TimeUtil.getRandMem();
        Response response = DepartmentObject.updateDepartment(access_token,dapartmentId,newName,newNameEn);
        assertEquals("0",response.path("errcode").toString());

    }
}
