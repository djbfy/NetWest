package com.qiqi.netnest.UtilsTest;

import com.qiqi.netnest.Utils.PasswdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswdUtilTest {
    @Test
    public void generatePass(){
        String ps1="123456";
        String s = PasswdUtil.generatePass("123456");
        System.out.println(s);
        System.out.println(PasswdUtil.judPass(ps1, s));
        System.out.println(PasswdUtil.judPass("12", s));
    }
}
