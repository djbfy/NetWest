package com.qiqi.netnest.Exception;

import com.qiqi.netnest.Enum.ReturnCode;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException{
    private int code;
    private String msg;

    public BusinessException(){
    }

    public BusinessException(ReturnCode returnCode){
        this(returnCode.getCode(),returnCode.getMsg());
    }

    public BusinessException(int code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }
}
