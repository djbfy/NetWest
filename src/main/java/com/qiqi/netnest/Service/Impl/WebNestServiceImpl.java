package com.qiqi.netnest.Service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qiqi.netnest.Enum.ReturnCode;
import com.qiqi.netnest.Exception.BusinessException;
import com.qiqi.netnest.Mapper.WebNestMapper;
import com.qiqi.netnest.Pojo.WebDomain;
import com.qiqi.netnest.Pojo.WebNest;
import com.qiqi.netnest.Service.HttpService;
import com.qiqi.netnest.Service.WebNestService;
import com.qiqi.netnest.Utils.DateUtil;
import com.qiqi.netnest.Utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebNestServiceImpl implements WebNestService {

    private static final Logger logger = LoggerFactory.getLogger(WebNestServiceImpl.class);

    @Autowired
    private WebNestMapper webNestMapper;

    @Autowired
    private HttpService httpService;

    @Override
    public List<WebNest> getAllItems() {
        try {
            return webNestMapper.selectList(new QueryWrapper<>());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WebNest> getItemsByTag(String tag) {
        try {
            return webNestMapper.selectList(new QueryWrapper<WebNest>().eq("tag", tag));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WebNest> getItemLike(String key) {
        String trimmedKey = StrUtil.trim(key);
        String lowercaseKey = trimmedKey.toLowerCase();
        String cleanedKey = StringUtil.filter(lowercaseKey);
        if (StrUtil.isEmpty(cleanedKey)) {
            throw new BusinessException(ReturnCode.RC999.getCode(), "特殊符号" + key);
        }
        try {
            return webNestMapper.selectList(new QueryWrapper<WebNest>()
                    .like("LOWER(title)", "%" + cleanedKey + "%")
                    .or().like("LOWER(url)", "%" + cleanedKey + "%")
                    .or().like("LOWER(description)", "%" + cleanedKey + "%"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public int addItem(WebNest webNest) {
        webNest.setCreateTime(DateUtil.getNowDate());
        if (StrUtil.isEmpty(webNest.getFavicon()) || StrUtil.isBlank(webNest.getFavicon())) {
            WebDomain webDomain = httpService.httpInfo(webNest.getUrl());
            if (webDomain == null||StrUtil.isBlank(webDomain.getFaviconUrl())) {
                webNest.setFavicon("auto.png");
                webNest.setGhm(1);
            } else {
                webNest.setFavicon(webDomain.getFaviconUrl());
                webNest.setGhm(0);
            }
        }
        try {
            return webNestMapper.insert(webNest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delItems(List<Integer> ids) {
        try {
            return webNestMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateItem(WebNest webNest) {
        try {
            if (webNest.getGhm()==0){
                WebDomain webDomain = httpService.httpInfo(webNest.getUrl());
                logger.info("updateItem获取网站头像："+webDomain.getFaviconUrl());
                webNest.setFavicon(webDomain.getFaviconUrl());
            }
            return webNestMapper.updateById(webNest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateStatus(Integer id, Integer status) {
        try {
            UpdateWrapper<WebNest> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);
            WebNest webNest = new WebNest();
            webNest.setStatus(status);
            return webNestMapper.update(webNest, updateWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
