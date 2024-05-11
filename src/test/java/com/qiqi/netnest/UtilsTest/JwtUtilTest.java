package com.qiqi.netnest.UtilsTest;

import com.qiqi.netnest.Service.VerifyService;
import com.qiqi.netnest.Utils.DateUtil;
import com.qiqi.netnest.Utils.JwtUtil;
import com.qiqi.netnest.Utils.PasswdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    VerifyService verifyService;
    @Test
    public void createToken(){
//        String s = verifyService.resetPass("123123");
//        String token = verifyService.verify("123123");
//        System.out.println(token);
//        System.out.println(JwtUtil.verify(token));
        Timestamp nowDate = DateUtil.getNowDate();
        System.out.println(nowDate);
    }
}
