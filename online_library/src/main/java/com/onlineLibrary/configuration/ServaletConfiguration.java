package com.onlineLibrary.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class ServaletConfiguration {

    // 配置请求日志过滤器
    @Bean
    public FilterRegistrationBean<CommonsRequestLoggingFilter> logFilter() {
        // 创建 CommonsRequestLoggingFilter 对象
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();

        // 设置过滤器的配置
        loggingFilter.setIncludeQueryString(true);  // 是否包括查询字符串
        loggingFilter.setIncludePayload(true);      // 是否包括请求体内容
        loggingFilter.setIncludeHeaders(true);      // 是否包括请求头
        loggingFilter.setMaxPayloadLength(10000);   // 设置最大请求体长度

        // 创建 FilterRegistrationBean 并注册过滤器
        FilterRegistrationBean<CommonsRequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(loggingFilter);
        registrationBean.addUrlPatterns("/api/*");  // 仅对指定路径生效（这里是 /api/*）

        return registrationBean;
    }
}
