package com.daviesjames.drawing2.filters;

import com.daviesjames.drawing2.repos.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class LoginFilter implements HandlerInterceptor {
    @Autowired
    UserRepo userRepo;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object o = session.getAttribute("userId");
        if (o == null) response.sendRedirect("/login");
        return true;
    }

}
