package com.qiqi.netnest.Config;

import com.qiqi.netnest.Filter.VerifyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VerifyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/tag/getTags")
                .excludePathPatterns("/webNest/get**")
                .excludePathPatterns("/html/**")
                .excludePathPatterns("/user/**");
    }
}
