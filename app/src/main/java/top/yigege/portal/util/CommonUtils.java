package top.yigege.portal.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanmagicpocket on 17/3/6.
 */

public class CommonUtils {

    //是否为空
    public static boolean isEmpty(String str){

        if(str != null && (str.length() > 0)){
            return  true;
        }

        return false;
    }

    public static boolean isBlack(String str ) {
        if (null == str || "".equals(str.trim())) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean isPhoneNumber(String str){
        final String regStr = "^1[0-9]{10}$";
        Pattern p = Pattern.compile(regStr);
        Matcher m = p.matcher(str);

        while(m.find()){ //注意这里，是while不是if
            String xxx = m.group();
            System.out.println("res ="+xxx);

            return true;
        }

        return false;
    }

    public static boolean isNumber(String str){
        final String regStr = "^[0-9]{6,20}$";
        Pattern p = Pattern.compile(regStr);
        Matcher m = p.matcher(str);

        while(m.find()){ //注意这里，是while不是if
            String xxx = m.group();
            System.out.println("res ="+xxx);

            return true;
        }

        return false;
    }

    public static   boolean isValidatePassword(String str){
        final String regStr = "^[A-Za-z0-9]{6,20}$";
        Pattern p = Pattern.compile(regStr);
        Matcher m = p.matcher(str);

        while(m.find()){ //注意这里，是while不是if
            String xxx = m.group();
            System.out.println("res ="+xxx);

            return true;
        }

        return false;
    }

    public  static boolean isValidateUserName(String str){
        final String regStr = "^[\\u4e00-\\u9fa50-9A-Za-z]{2,20}$";
        Pattern p = Pattern.compile(regStr);
        Matcher m = p.matcher(str);

        while(m.find()){ //注意这里，是while不是if
            String xxx = m.group();
            System.out.println("res ="+xxx);

            return true;
        }

        return false;
    }

    public static boolean isIdentityCardNumber(String str){
        final String regStr = "^(\\\\d{14}|\\\\d{17})(\\\\d|[xX])$";
        Pattern p = Pattern.compile(regStr);
        Matcher m = p.matcher(str);

        while(m.find()){ //注意这里，是while不是if
            String xxx = m.group();
            System.out.println("res ="+xxx);

            return true;
        }

        return false;
    }


    //pengyi add
    private final static long MINUTE = 60 * 1000;// 1分钟
    private final static long HOUR = 60 * MINUTE;// 1小时
    private final static long DAY = 24 * HOUR;// 1天
    private final static long MONTH = 31 * DAY;// 月
    private final static long YEAR = 12 * MONTH;// 年

    public static void main(String args[]){
        long lt = new Long("1541769770");
        Date date = new Date(lt * 1000);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(simpleDateFormat.format(date));
        System.out.print(getTimeFormatText(date));
    }

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - date.getTime();
        long r = 0;
        if (diff > YEAR) {
            r = (diff / YEAR);
            return r + "年前";
        }
        if (diff > MONTH) {
            r = (diff / MONTH);
            return r + "月前";
        }
        if (diff > DAY) {
            r = (diff / DAY);
            return r + "天前";
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "小时前";
        }
        if (diff > MINUTE) {
            r = (diff / MINUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }

}
