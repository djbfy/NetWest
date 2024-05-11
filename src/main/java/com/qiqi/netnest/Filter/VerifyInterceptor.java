package com.qiqi.netnest.Filter;

import cn.hutool.core.util.StrUtil;
import com.qiqi.netnest.Utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyInterceptor implements HandlerInterceptor {
    private static final Logger logger= LoggerFactory.getLogger(VerifyInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StrUtil.isEmptyIfStr(token)){
           logger.warn("token为空");
           response.getWriter().write("token is null");
            return false;
        }
        if (!JwtUtil.verify(token)){
            logger.warn("token无效");
            response.getWriter().write("token expire");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
