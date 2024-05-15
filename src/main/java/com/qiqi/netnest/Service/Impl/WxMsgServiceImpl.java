package com.qiqi.netnest.Service.Impl;

import cn.hutool.core.date.DateUtil;
import com.qiqi.netnest.Pojo.WxConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class WxMsgServiceImpl {
    @Resource
    private WxConfig wxConfig;

    public void sendMsg(String ip,String position,String status) {
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(wxConfig.getAppId());
        config.setSecret(wxConfig.getAppSecret());
        wxMpService.setWxMpConfigStorage(config);
        WxMpTemplateMessage build = WxMpTemplateMessage.builder()
                .toUser(wxConfig.getOpenId())
                .templateId(wxConfig.getTemplateId())
                .url("http://82.157.189.168/")
                .build();
        String now = DateUtil.now();
        WxMpTemplateData data = new WxMpTemplateData("ip",ip,"#173177");
        WxMpTemplateData data1 = new WxMpTemplateData("position",position,"#173177");
        WxMpTemplateData data2 = new WxMpTemplateData("time",now,"#173177");
        WxMpTemplateData data3 = new WxMpTemplateData("status",status,"#173177");
        build.addData(data);
        build.addData(data1);
        build.addData(data2);
        build.addData(data3);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(build);
        } catch (WxErrorException e) {
            log.error(e.getMessage());
        }
    }
}
