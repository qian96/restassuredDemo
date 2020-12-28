package MemberDemo;

import Base.GetToken;
import Base.MemberObject;
import Utils.TimeUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author wangqian
 * @Date 2020-12-25 14:36
 * @Version 1.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberDemo {
    static String access_token;

    @BeforeAll
    public static void demo1() {
        access_token = GetToken.getToken();
    }

    @DisplayName("创建成员")
    @Test
     void createMem(){
        String userId = TimeUtil.getRandMem();
        String userName = TimeUtil.getUserName();
        String telephone = TimeUtil.getTel();
        String email = TimeUtil.getEmail();
        Response response = MemberObject.creatMenber(access_token, userId, userName, email,telephone);
        assertEquals("0",response.path("errcode").toString());
    }
    @DisplayName("修改成员")
    @Test
    void updateMember(){
        String userId = MemberObject.getMemberId(access_token);
        String updateName = TimeUtil.getUserName();
        Response response = MemberObject.updateMember(access_token,userId,updateName);
        assertEquals("0",response.path("errcode").toString());
    }
    @DisplayName("查询成员")
    @Test

    void searchMember(){
        Response response = MemberObject.listMember(access_token);
        assertEquals("0",response.path("errcode").toString());
    }
    @DisplayName("删除成员")
    @Test
    void deleteMember(){
        String userId = MemberObject.getMemberId(access_token);
        Response response = MemberObject.deleteMember(access_token, userId);
        assertEquals("0",response.path("errcode").toString());
    }
}
