package com.qiqi.netnest.Vo.Response;

import com.qiqi.netnest.Pojo.WebDomain;
import com.qiqi.netnest.Pojo.WebNest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebNestResponse {
    private WebNest webNest;
    private WebDomain webDomain;
}
