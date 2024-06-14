package com.qiqi.netnest.Controller;

import com.qiqi.netnest.Service.Impl.WxCosServiceImpl;
import com.qiqi.netnest.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/image")
public class CosController {

    @Autowired
    private WxCosServiceImpl wxCosService;

    @GetMapping("/bucketObj")
    public Result<HashMap<String, String>> getObject(){
        HashMap<String, String> object = wxCosService.getObject();
        return Result.success(object);
    }
}
