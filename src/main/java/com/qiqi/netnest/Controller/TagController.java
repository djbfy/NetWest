package com.qiqi.netnest.Controller;

import com.qiqi.netnest.Enum.ReturnCode;
import com.qiqi.netnest.Pojo.Tag;
import com.qiqi.netnest.Service.TagService;
import com.qiqi.netnest.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/getTags")
    public Result<List<Tag>> getTags(){
        return Result.success(tagService.getTags());
    }

    @PostMapping("/insetTag")
    public Result<String> insertTag(@RequestBody Tag tag){
        tagService.insertTag(tag);
        return Result.ok(ReturnCode.RC200.getCode(),"新增tag成功");
    }
}
