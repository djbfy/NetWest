package com.qiqi.netnest.Exception;

import com.qiqi.netnest.Enum.ReturnCode;
import com.qiqi.netnest.Vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public Result<String> businessException(BusinessException e) {
        logger.error("业务异常 code={}, BusinessException = {}", e.getCode(), e.getMessage(), e);
        return Result.error(e.getCode(),e.getMsg());
    }
    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e) {
        logger.error("异常 exception = {}", e.getMessage(), e);
        return Result.error(ReturnCode.RC500.getCode(), ReturnCode.RC500.getMsg());
    }
}
