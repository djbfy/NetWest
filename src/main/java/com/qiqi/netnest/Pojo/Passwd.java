package com.qiqi.netnest.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("passwd")
public class Passwd {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String role;
    private String password;

}
