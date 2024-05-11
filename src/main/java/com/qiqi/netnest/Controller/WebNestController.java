package com.qiqi.netnest.Controller;

import com.qiqi.netnest.Annotation.LogParams;
import com.qiqi.netnest.Enum.ReturnCode;
import com.qiqi.netnest.Pojo.WebNest;
import com.qiqi.netnest.Service.WebNestService;
import com.qiqi.netnest.Vo.Request.UpdateStatusRequest;
import com.qiqi.netnest.Vo.Response.WebNestResponse;
import com.qiqi.netnest.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/webNest")
@Validated
public class WebNestController {
    @Autowired
    private WebNestService webNestService;

    @GetMapping("/getAllItems")
    public Result<List<WebNest>> getAllItems() {
        List<WebNest> allItems = webNestService.getAllItems();
        return Result.success(allItems);
    }

    @GetMapping("/getItemsByTag")
    public Result<List<WebNest>> getItemsByTag(@RequestParam(value = "tag") String tag){
        List<WebNest> itemsByTag = webNestService.getItemsByTag(tag);
        return Result.success(itemsByTag);
    }

    @GetMapping("/getItemLike")
    @LogParams
    public Result<List<WebNest>> getItemLike(@RequestParam(value = "key") String key){
        List<WebNest> itemsByKey = webNestService.  getItemLike(key);
        return Result.success(itemsByKey);
    }

    @PostMapping("/addItem")
    @LogParams
    public Result<String> addItem(@Valid @RequestBody WebNest webNest) {
        webNestService.addItem(webNest);
        return Result.ok(ReturnCode.RC200);
    }

    @DeleteMapping("/delItem/{ids}")
    @LogParams
    public Result<String> delItem(@PathVariable List<Integer> ids) {
        webNestService.delItems(ids);
        return Result.ok(ReturnCode.RC200);
    }

    @PostMapping("/updateItem")
    @LogParams
    public Result<String> updateItem(@RequestBody WebNest webNest) {
        webNestService.updateItem(webNest);
        return Result.ok(ReturnCode.RC200);
    }

    @PostMapping("/updateStatus")
    @LogParams
    public Result<String> updateStatus(@RequestBody UpdateStatusRequest updateStatusRequest) {
        webNestService.updateStatus(updateStatusRequest.getId(), updateStatusRequest.getStatus());
        return Result.ok(ReturnCode.RC200);
    }
}
