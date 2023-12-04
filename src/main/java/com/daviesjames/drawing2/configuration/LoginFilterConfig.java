package com.daviesjames.drawing2.configuration;

import com.daviesjames.drawing2.filters.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginFilterConfig implements WebMvcConfigurer {
    @Autowired
    LoginFilter loginFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginFilter)
                .addPathPatterns("/paint", "/myImages", "/view", "/modify", "/bin");
    }
}
