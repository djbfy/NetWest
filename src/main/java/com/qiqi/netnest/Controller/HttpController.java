package com.qiqi.netnest.Controller;

import com.qiqi.netnest.Service.HttpService;
import com.qiqi.netnest.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/html")
public class HttpController {

    @Autowired
    private HttpService httpService;

    @GetMapping("/favicon")
    public Result httpWeb(@RequestParam String url) {
        return Result.success(httpService.httpInfo(url));
    }


}
