package com.qiqi.netnest.Controller;

import cn.hutool.json.JSONObject;
import com.qiqi.netnest.Service.VerifyService;
import com.qiqi.netnest.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class VerifyController {
    @Autowired
    private VerifyService verifyService;

    @PostMapping("/resetPass")
    public Result<String> resetPass(@RequestBody String pass){
        String password  = new JSONObject(pass).getStr("pass");
        return verifyService.resetPass(password);
    }

    @PostMapping("/verify")
    public Result<String> verify(@RequestBody String pass){
        String password  = new JSONObject(pass).getStr("pass");
        return verifyService.verify(password);
    }

}
