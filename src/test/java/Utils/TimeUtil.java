package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @Author wangqian
 * @Date 2020-12-24 16:47
 * @Version 1.0
 */
public class TimeUtil {
    /**
     * 获取当前时间戳
     * @return
     */
   /* public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(String.valueOf(new Date().getTime()));
        return date;
    }*/
    public static String getCurrentUnixTime() {
        return getUnixTimeByDate(new Date());
    }
    public static String getUnixTimeByDate(Date date) {
        return  (String.valueOf(date.getTime()));
    }
    /**
     * 生成随机数
     */
    public static String getRandMem(){
        Random r = new Random();
        int i = r.nextInt(10000);
        String s = String.valueOf(i);
        return s;
    }

    public static String getUserName(){
        String val ="";
        Random random = new Random();
        for (int i=0;i<5;i++){
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
                // int choice = 97; // 指定字符串为小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
       return  val;
    }

    //随机获取手机号
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }
   //email
    private static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String getEmail() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 20; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }


}
