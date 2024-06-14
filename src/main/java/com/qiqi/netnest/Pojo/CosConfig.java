package com.qiqi.netnest.Pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CosConfig {

    @Value(value = "${wx.cosSecretId}")
    private String secretId;

    @Value(value = "${wx.cosSecretKey}")
    private String secretKey;
}
