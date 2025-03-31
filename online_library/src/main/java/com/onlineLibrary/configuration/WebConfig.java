package com.onlineLibrary.configuration;

import com.github.pagehelper.PageHelper;
import com.onlineLibrary.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

/**
 * 拦截器配置类
 * 搭配拦截器一起使用
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login",
                        "/register01",
                        "/logout",
                        "/home/**",
                        "/publication/**");

    }

    //确保 PageHelper 被正确配置并且插件被加载到 MyBatis 配置中
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");  // 设置数据库类型为 MySQL
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
