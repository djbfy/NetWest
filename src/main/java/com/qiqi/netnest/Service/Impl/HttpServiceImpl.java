package com.qiqi.netnest.Service.Impl;

import cn.hutool.core.util.StrUtil;
import com.qiqi.netnest.Pojo.WebDomain;
import com.qiqi.netnest.Service.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//todo 改用jsoup来操作html
@Service
public class HttpServiceImpl implements HttpService {

    private static final Logger logger = LoggerFactory.getLogger(HttpServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WebDomain httpInfo(String url) {
        String regexUrl = "(http[s]?:\\/\\/[^\\/]*)";
        //获取主域名
        String urls = extractHtml(url, regexUrl);
        String htmlContent = null;
        try {
            htmlContent = restTemplate.getForObject(urls, String.class);
        } catch (RestClientException e) {
            logger.error("获取{" + url + "}源码失败");
            logger.error(e.getMessage());
            return null;
        }
        if (StrUtil.isEmpty(htmlContent)) {
            logger.error("获取{" + url + "}源码为空");
            return null;
        }
        //匹配<link rel="*icon*" href="()"> ()内内容
        String regexIcon = "<link\\s+[^>]*?rel=['\"]?[^'\">]*icon[^'\">]*['\"]?\\s+[^>]*?href=['\"]?([^'\"\\s>]+)['\"]?[^>]*?>";
        String regexTitle = "<title>(.*?)</title>";
        String regexDes = "<meta\\s+name=\"description\"\\s+content=\"([^\"]+)\">";
        String baseUrl = extractHtml(htmlContent, regexIcon);
        //获取//前 http(s):
        String preUrls = extractHtml(urls, "(http[s]?:)\\/\\/[^\\/]*");
        if (StrUtil.isNotEmpty(baseUrl) && !baseUrl.startsWith("http")) {
            if (baseUrl.startsWith("//")) {
                baseUrl = preUrls + baseUrl;
            } else if (!baseUrl.startsWith("/")) {
                baseUrl = urls +"/"+ baseUrl;
            } else {
                baseUrl = urls + baseUrl;
            }
        }
        String baseTitle = extractHtml(htmlContent, regexTitle);
        String baseDes = extractHtml(htmlContent, regexDes);
        return new WebDomain(baseUrl, baseTitle, baseDes);
    }

    public String extractHtml(String htmlContent, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlContent);
        if (matcher.find()) {
            return matcher.group(1);
        }
        logger.warn("未匹配到目标标签->" + regex);
        return null;
    }
}
