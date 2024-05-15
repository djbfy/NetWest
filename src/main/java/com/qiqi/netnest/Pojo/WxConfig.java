package com.qiqi.netnest.Pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class WxConfig {

    @Value(value = "${wx.appId}")
    private String appId;

    @Value(value = "${wx.appSecret}")
    private String appSecret;

    @Value(value = "${wx.templateId}")
    private String templateId;

    @Value(value = "${wx.openId}")
    private String openId;
}
