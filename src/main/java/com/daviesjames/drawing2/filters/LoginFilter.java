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

    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("userId");
        if (!userRepo.findById(id)){
            resp.sendRedirect("/login");
        }
        return true;
    }
}
