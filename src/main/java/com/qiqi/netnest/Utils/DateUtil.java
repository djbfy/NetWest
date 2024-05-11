package com.qiqi.netnest.Utils;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
    public static Timestamp getNowDate(){
        System.setProperty("user.timezone", "Asia/Shanghai");
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
