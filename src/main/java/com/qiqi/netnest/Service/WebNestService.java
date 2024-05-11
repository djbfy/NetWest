package com.qiqi.netnest.Service;

import com.qiqi.netnest.Pojo.WebNest;
import com.qiqi.netnest.Vo.Response.WebNestResponse;

import java.util.List;


public interface WebNestService {
    List<WebNest> getAllItems();

    List<WebNest> getItemsByTag(String tag);

    List<WebNest> getItemLike(String key);

    int addItem(WebNest webNest);

    int delItems(List<Integer> ids);

    int updateItem(WebNest webNest);

    int updateStatus(Integer id,Integer status);

}
