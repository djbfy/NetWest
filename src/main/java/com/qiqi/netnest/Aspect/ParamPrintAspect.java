package com.qiqi.netnest.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ParamPrintAspect {

    private static final Logger logger=LoggerFactory.getLogger(ParamPrintAspect.class);

    @Pointcut("@annotation(com.qiqi.netnest.Annotation.LogParams)")
    public void LogParams(){}

    @Before("LogParams()")
    public void beforeMethodsLogParams(JoinPoint joinPoint){
        String beanName = joinPoint.getSignature().getName();
        logger.info("Method-"+beanName+":入参:"+ Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "LogParams()",returning = "result")
    public void afterMethodsLog(JoinPoint joinPoint,Object result){
        String beanName = joinPoint.getSignature().getName();
        logger.info("Method-"+beanName+":返回结果:"+ result);
    }
}

