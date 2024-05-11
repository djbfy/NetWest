package com.qiqi.netnest.UtilsTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.sql.Timestamp;

@SpringBootTest
public class DateUtil {
    @Test
    public void dateTest(){
        System.setProperty("user.timezone", "Asia/Shanghai");

        // 打印当前默认时区
        System.out.println("当前默认时区: " + System.getProperty("user.timezone"));

        // 输出当前时间，观察是否受到时区设置影响
        System.out.println("当前时间: " + java.time.LocalDateTime.now());
        Date date = new Date();
        long time = date.getTime();
        System.out.println(new Timestamp(time));
    }
}
