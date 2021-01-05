package frameworkwechat.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;
import frameworkwechat.actions.ApiActionModel;
import frameworkwechat.api.ApiObjectModel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author wangqian
 * @Date 2021-01-05 11:13
 * @Version 1.0
 */
public class HarToYamlTest {

    @Test
    void harTest() throws HarReaderException {
        HarReader harReader = new HarReader();
        Har har =  harReader.readFromFile(new File("src/test/resources/har/qyapi.weixin.qq.com.har"));

        ApiActionModel apiActionModel = new ApiActionModel();
        ApiObjectModel apiObjectModel = new ApiObjectModel();
        HashMap<String,ApiActionModel> actions = new HashMap<>();
        HashMap<String,String> queryMap = new HashMap<>();
        har.getLog().getEntries().forEach(entrie->{
            HarRequest harRequest = entrie.getRequest();
            harRequest.getQueryString().forEach(query->{
                queryMap.put(query.getName(),query.getValue());
            });
            String method = harRequest.getMethod().toString();
            String url = harRequest.getUrl();
            apiActionModel.setQuery(queryMap);
            if(method.equals("get")){
                apiActionModel.setGet(url);
            }else{
                apiActionModel.setPost(url);
            }
            actions.put(getRequestName(url),apiActionModel);
            apiObjectModel.setName("getToken");
            apiObjectModel.setActions(actions);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                mapper.writeValue(new File("src/test/resources/har/getToken.yaml"),apiObjectModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    @Test
    void test2() throws Exception {
        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/har/getToken.yaml");
        apiObjectModel.getActions().get("gettoken").run(null);
    }
    public String getRequestName(String url) {
        String[] suburl = url.split("\\u003F")[0].split("/");
        String name = "";
        if (suburl.length > 1) {
            name = suburl[suburl.length - 1];
        }else if(1==suburl.length){
            name = suburl[0];
        }
        return name;
    }
}
