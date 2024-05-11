package com.qiqi.netnest.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "webNest")
public class WebNest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 图标
     */
    private String favicon;
    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;
    /**
     * url
     */
    @NotBlank(message = "url不能为空")
    private String url;
    /**
     * 描述
     */
    private String description;
    /**
     * tag
     */
    private String tag;
    /**
     * status
     */
    private Integer status;
    /**
     * 是否获取页面信息
     */
    private Integer ghm;
    /**
     * 创建日期
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Timestamp createTime;
}
