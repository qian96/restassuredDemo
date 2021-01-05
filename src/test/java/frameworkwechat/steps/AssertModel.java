package frameworkwechat.steps;

/**
 * @Author wangqian
 * @Date 2020-12-31 17:18
 * @Version 1.0
 */
public class AssertModel {
    private String actual;
    private String matcher;
    private String expect;
    private String reason;

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
