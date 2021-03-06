package asyncload;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTime {

    public static String time() {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        //sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();// 获取当前时间
        String time = sdf.format(date);
        //System.out.println("现在时间：" + time); // 输出已经格式化的现在时间（24小时制）
        return time;
    }

    public static void log(String args) {
        System.out.println(time() + ", " + args);
    }
}
