package com.daviesjames.drawing2.filters;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class LoginFilterConfig implements WebMvcConfigurer {
    public void addInterceptor(LoginFilter log){
    }
}
