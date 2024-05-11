package com.qiqi.netnest.Vo;

import com.qiqi.netnest.Enum.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ReturnCode.RC200.getCode());
        result.setMsg(ReturnCode.RC200.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(int code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> ok(ReturnCode r) {
        return new Result<>(r.getCode(), r.getMsg());
    }
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg);
    }
}
